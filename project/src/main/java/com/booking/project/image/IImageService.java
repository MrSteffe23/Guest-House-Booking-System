package com.booking.project.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Interface which separates the database from the java logic. <br>
 * Here are all the methods used to interact with the lower level of database
 */
public interface IImageService {
    /**
     * Get all the Images in the database
     * @return The list of the Images found in the database
     */
    List<Image> getAllImages();

    /**
     * Get an Image by an id
     * @return The Image with the specified id
     */
    Image getImageById(Long id_image);

    /**
     * Get an Image by a type and a number
     * @return The Image with the specified type and number
     */
    Image getImageByType(String type, int number);

    /**
     * Get an Image by a type
     * @return The Image with the specified type
     */
    List<Image> getImagesByType(String type);

    /**
     * Method used to insert a new Image in the database
     * @param file a new Image to insert in the database
     * @param type the type of the photo
     */
    Image createImage(MultipartFile file, String type) throws IOException;

    /**
     * Method used to delete an Image from the database (if the Image exists).
     * @param id the id of the Image to be deleted.
     */
    void deleteImage(Long id);
}
