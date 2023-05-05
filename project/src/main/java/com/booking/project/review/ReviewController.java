package com.booking.project.review;

import com.booking.project.reservation.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link ReviewService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final IReviewService reviewService;

    /**
     * Constructor which have the role to implement Dependency Injection for the reviewService attribute.
     * @param reviewService the reference to the Service layer.
     */
    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Reviews in the database, regardless of the house they belong to.
     */
    @GetMapping
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @param id the id of the House whose reviews I want to obtain.
     * @return a list with all the Reviews in the database, associated with a specified house by that id variable.
     */
    @GetMapping("/{id}")
    public List<Review> getReviews(@PathVariable Long id){
        return reviewService.getReviews(id);
    }

    /**
     * Inserts a new Review in the database, if possible.
     * @param review JSON with all the data for a Review
     */
    @PostMapping
    public void createReview(@RequestBody Review review){
        reviewService.createReview(review);
    }

    /**
     * Deletes a Review from the database, if possible.
     * @param id the id of the Review to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }

    /**
     * Updates a Review from the database with new specifications.
     * @param id the id of the Review to be updated.
     * @param review the Review with new specifications.
     */
    @PutMapping("/{id}")
    public void updateReview(@RequestBody Review review, @PathVariable Long id){
        reviewService.updateReview(review, id);
    }
}
