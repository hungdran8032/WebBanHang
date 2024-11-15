package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.exceptions.ProductException;
import com.ecommerce.WebEcommerce.model.Category;
import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.Seller;
import com.ecommerce.WebEcommerce.repository.CategoryRepository;
import com.ecommerce.WebEcommerce.repository.ProductRepository;
import com.ecommerce.WebEcommerce.request.CreateProductRequest;
import com.ecommerce.WebEcommerce.service.ProductService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public Product createProduct(CreateProductRequest request, Seller seller) {
        Category category1 = categoryRepository.findByCategoryId(request.getCategory1());
        if(category1==null){
            Category newCategory = new Category();
            newCategory.setCategoryId(request.getCategory1());
            newCategory.setLevel(1);
            category1 = categoryRepository.save(newCategory);
        }

        Category category2 = categoryRepository.findByCategoryId(request.getCategory2());
        if(category2==null){
            Category newCategory = new Category();
            newCategory.setCategoryId(request.getCategory2());
            newCategory.setLevel(2);
            newCategory.setParentCategory(category1);
            category2 = categoryRepository.save(newCategory);
        }

        Category category3 = categoryRepository.findByCategoryId(request.getCategory3());
        if(category3==null){
            Category newCategory = new Category();
            newCategory.setCategoryId(request.getCategory3());
            newCategory.setLevel(3);
            newCategory.setParentCategory(category2);
            category3 = categoryRepository.save(newCategory);
        }

        int discountPercentage = calculateDiscountPercentage(request.getMrpPrice(),request.getSellingPrice());

        Product newProduct = new Product();
        newProduct.setSeller(seller);
        newProduct.setCategory(category3);
        newProduct.setCreateAt(LocalDateTime.now());
        newProduct.setColor(request.getColor());
        newProduct.setDescription(request.getDescription());
        newProduct.setImages(request.getImages());
        newProduct.setDiscountPercent(discountPercentage);
        newProduct.setSize(request.getSize());
        newProduct.setMrpPrice(request.getMrpPrice());
        newProduct.setSellingPrice(request.getSellingPrice());
        newProduct.setTitle(request.getTitle());

        return productRepository.save(newProduct);
    }

    private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
        if (mrpPrice <= 0) {
            throw new IllegalArgumentException("Giá gốc phải lớn hơn 0");
        }
        double discount = (mrpPrice - sellingPrice);
        double discountPercentage = (discount / mrpPrice) * 100;
        return (int) discountPercentage;
    }

    @Override
    public Product updateProduct(Long idProduct, Product product) throws ProductException {
        getProductById(idProduct);
        product.setId(idProduct);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long idProduct) throws ProductException {
        Product findProduct = getProductById(idProduct);
        productRepository.delete(findProduct);
    }

    @Override
    public Product getProductById(Long idProduct) throws ProductException {

        return productRepository.findById(idProduct).orElseThrow(()-> new ProductException("Khong tim thay san pham co id "+ idProduct));
    }

    @Override
    public List<Product> searchProduct(String query) {

        return productRepository.searchProduct(query);
    }

    @Override
    public Page<Product> getAllProduct(String category, String brand, String color, String size, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber) {
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(category!=null){
                Join<Product,Category> categoryJoin = root.join("category");
                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
            }
            if(color != null && !color.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("color"), color));
            }
            if(size != null && !size.isEmpty()){
                predicates.add(criteriaBuilder.equal(root.get("size"), size));
            }
            if(minPrice != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }
            if(maxPrice != null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"), minPrice));
            }
            if(minDiscount != null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"), minDiscount));
            }
            if(stock != null){
                predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Pageable pageable;
        if (sort != null && !sort.isEmpty()){
            pageable = switch (sort) {
                case "price_low" -> PageRequest.of(
                        pageNumber != null ? pageNumber : 0,
                        10,
                        Sort.by("sellingPrice").ascending()
                );
                case "price_high" -> PageRequest.of(
                        pageNumber != null ? pageNumber : 0,
                        10,
                        Sort.by("sellingPrice").descending()
                );
                default -> PageRequest.of(
                        pageNumber != null ? pageNumber : 0,
                        10,
                        Sort.unsorted()
                );
            };
        }else {
            pageable = PageRequest.of(
                    pageNumber!=null ? pageNumber: 0,
                    10,
                    Sort.unsorted());
        }
        return productRepository.findAll(spec,pageable);
    }

    @Override
    public List<Product> getProductBySellerId(Long idSeller) {

        return productRepository.findSellerById(idSeller);
    }
}
