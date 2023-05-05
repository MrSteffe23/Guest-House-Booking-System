package com.booking.project;

import com.booking.project.review.IReviewService;
import com.booking.project.review.Review;
import com.booking.project.review.ReviewRepository;
import com.booking.project.review.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class is used to test the implementation of the class {@link com.booking.project.review.ReviewService}
 */
@SpringBootTest
public class ReviewTests {
    @Mock
    private ReviewRepository reviewRepositoryMock;

    @Test
    void testCreateReview() {
        Review review = new Review(1L, 1L, 2L, "foarte frumos", 5L);
        //when(reviewRepositoryMock.save(review)).thenReturn(review);
        IReviewService reviewService = new ReviewService(reviewRepositoryMock);
        reviewService.createReview(review);

        verify(reviewRepositoryMock).save(review);
    }

    @Test
    void testDeleteReview() {
        Long id = 1L;
        Review review = new Review(1L, 1L, 2L, "o locatie frumoasa", 5L);
        Optional<Review> reviewOptional = Optional.ofNullable(review);
        when(reviewRepositoryMock.findById(id)).thenReturn(reviewOptional);

        IReviewService reviewService = new ReviewService(reviewRepositoryMock);
        reviewService.deleteReview(id);
        verify(reviewRepositoryMock).findById(id);
        verify(reviewRepositoryMock).deleteById(id);
    }

    @Test
    void testUpdateReview() {
        Long id = 1L;
        Review reviewToBeUpdated = new Review(1L, 1L, 2L, "o locatie frumoasa", 5L);
        Optional<Review> reviewOptional = Optional.ofNullable(reviewToBeUpdated);
        Review newReview = new Review(1L, 1L, 2L,
                "o locatie frumoasa, intre munti, aer curat", 5L);
        when(reviewRepositoryMock.findById(id)).thenReturn(reviewOptional);

        IReviewService reviewService = new ReviewService(reviewRepositoryMock);
        reviewService.updateReview(newReview, id);
        assertEquals(reviewToBeUpdated, newReview);//verifying if the new review was updated
        verify(reviewRepositoryMock, times(2)).findById(id);
        verify(reviewRepositoryMock).save(reviewToBeUpdated);
    }
}
