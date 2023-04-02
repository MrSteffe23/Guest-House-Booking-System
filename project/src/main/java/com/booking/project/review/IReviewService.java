package com.booking.project.review;

import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IReviewService {

    /**
     * Get all Reviews in the database, regardless of the house they belong to.
     * @return The list of the Reviews found in the database
     */
    List<Review> getAllReviews();

    /**
     * Get all Reviews in the database for a specific house given by an id
     * @param id_house the id of the House whose reviews you want to obtain
     * @return The list of the Reviews for a specific house
     */
    List<Review> getReviews(Long id_house);

    /**
     * Method used to insert a new Review in the database
     * @param review a new Review to insert in the database
     */
    void createReview(Review review);

    /**
     * Method used to delete a Review from the database (if the Review exists).
     * @param id the id of the Review to be deleted.
     */
    void deleteReview(Long id);

    /**
     * Method used to update a Review with different details (if the Review exists).
     * @param id the id of the Review to be updated.
     * @param review the new specifications for the Review.
     */
    void updateReview(Review review, Long id);



}
