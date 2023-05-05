package com.booking.project.review;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IReviewService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class ReviewService implements IReviewService{

    /**
     * Attribute which represents the DataAccess layer.
     */
    private final ReviewRepository reviewRepository;

    /**
     * Constructor which have the role to implement Dependency Injection for the reviewRepository attribute.
     * @param reviewRepository the reference to the DataAccess layer.
     */
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * This method is used for display purposes. You can see all the reviews in the database, regardless of the house
     * they belong to.
     * @return all the reviews in the database in a List.
     */
    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Get all Reviews in the database for a specific house given by an id
     * @param id_house the id of the House whose reviews you want to get
     * @return The list of the Reviews for a specific house
     */
    @Override
    public List<Review> getReviews(Long id_house) {
        return reviewRepository.findByIdHouse(id_house);
    }

    /**
     * Creates a Review in the database, given the data in the parameter.
     * @param review JSON with data for a reservation.
     */
    @Override
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    /**
     * First, this method checks to see if the database have a review with the specified id. If yes,
     * the review is being deleted, otherwise, an exception is thrown.
     * @param id the id of the Review to be deleted.
     * @throws IllegalStateException if the database doesn't have a review with the specified id.
     */
    @Override
    public void deleteReview(Long id) {
        checkValidIdReview(id);
        reviewRepository.deleteById(id);
    }

    /**
     * First, this method checks to see if a Review with the specified id exists, so that it can be updated.<br>
     * Then, the Review extracted with the specified id is updated and then saved in the database.
     * @param id the id of the Review to be updated.
     * @param review the Review with new specifications.
     */
    @Override
    public void updateReview(Review review, Long id) {
        checkValidIdReview(id);
        Review reviewToUpdate = reviewRepository.findById(id).get();

        reviewToUpdate.setIdHouse(review.getIdHouse());
        reviewToUpdate.setIdUser(review.getIdUser());
        reviewToUpdate.setDescription(review.getDescription());
        reviewToUpdate.setStarsCount(review.getStarsCount());

        reviewRepository.save(reviewToUpdate);
    }

    /**
     * Functions which verifies if the database has a review with the specified id
     * @param id id of the review that you want to look for
     */
    public void checkValidIdReview(Long id){
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(!reviewOptional.isPresent()){
            throw new IllegalStateException(String.format("The Review with id %s doesn't exist.", id));
        }
    }
}
