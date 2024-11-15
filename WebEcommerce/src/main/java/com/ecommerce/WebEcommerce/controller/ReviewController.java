package com.ecommerce.WebEcommerce.controller;

import com.ecommerce.WebEcommerce.model.Product;
import com.ecommerce.WebEcommerce.model.Review;
import com.ecommerce.WebEcommerce.model.User;
import com.ecommerce.WebEcommerce.request.CreateReviewRequest;
import com.ecommerce.WebEcommerce.response.ApiResponse;
import com.ecommerce.WebEcommerce.service.ProductService;
import com.ecommerce.WebEcommerce.service.ReviewService;
import com.ecommerce.WebEcommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/product/{productId}/review")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId){
        List<Review> reviews = reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/product/{productId}/review")
    public ResponseEntity<Review> writeReview(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long productId,
            @RequestBody CreateReviewRequest req
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.getProductById(productId);

        Review reviews = reviewService.createReview(req, user,product);
        return ResponseEntity.ok(reviews);
    }

    @PatchMapping("/review/{reviewId}")
    public ResponseEntity<Review> updateReview(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long reviewId,
            @RequestBody CreateReviewRequest req
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Review reviews = reviewService.updateReview(
                reviewId, req.getReviewText(), req.getReviewRating(), user.getId()
        );
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReview(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long reviewId
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        reviewService.deleteReview(reviewId, user.getId());
        ApiResponse res = new ApiResponse();
        res.setMessage("Xoa thanh cong");
        return ResponseEntity.ok(res);
    }
}
