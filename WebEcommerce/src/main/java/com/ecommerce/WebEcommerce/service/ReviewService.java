package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.Review;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.request.CreateReviewRequest;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest req, User user, Product product);
    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;
    void deleteReview(Long reviewId, Long userId) throws Exception;
    Review getReviewById(Long reviewId) throws Exception;
}
