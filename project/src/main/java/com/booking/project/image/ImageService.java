package com.booking.project.image;

import com.booking.project.house.IHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class which implements the contract of the interface {@link IImageService}. <br>
 * It has the implementation of the methods for PostgreSQL database.
 * Here is all the logic of the application.
 */
@Service
public class ImageService implements IImageService{

    /**
     * Attribute which represents the DataAccess layer.
     */
    @Autowired
    private ImageRepository imageRepository;

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Images in the database.
     */
    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return an Image from the database found by an id.
     */
    @Override
    public Image getImageById(Long id_image) {
        checkValidImage(id_image);
        return imageRepository.findById(id_image).get();
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return an Image from the database found by a type.
     */
    @Override
    public Image getImageByType(String type, int number) {
        Image image = null;
        List<Image> images = imageRepository.findAll();
        int i = 1;
        //System.out.println("START");
        for (Image imageC : images) {
            /*System.out.println(imageC.getType());
            System.out.println(type);
            System.out.println(i);
            System.out.println(number);*/
            if(imageC.getType().equals(type))
            {
                if(i == number) {
                    image = imageC;
                    break;
                }
                else{
                    i++;
                }
            }
        }
        /*System.out.println("STOP");

        System.out.println(number);
        System.out.println(type);
        System.out.println(image);*/
        return image;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return an Image from the database found by a type and a number;
     */
    @Override
    public List<Image> getImagesByType(String type) {
        List<Image> result = new ArrayList<>();
        List<Image> images = imageRepository.findAll();
        for (Image imageC : images) {
            if(imageC.getType().equals(type))
            {
                result.add(imageC);
            }
        }
        return result;
    }

    /**
     * Inserts a new Image in the database, if possible.
     * @param file the image
     * @param type the type of the image
     */
    @Override
    public Image createImage(MultipartFile file, String type) throws IOException {
        return imageRepository.save(Image.builder()
                              .fileName(file.getOriginalFilename())
                              .imageData(file.getBytes())
                              .type(type)
                              .build());
    }

    /**
     * Deletes an Image from the database, if possible.
     * @param id the id of the Image to be deleted.
     */
    @Override
    public void deleteImage(Long id) {
        checkValidImage(id);
        imageRepository.deleteById(id);
    }

    /**
     * Checks if the database have an Image with that id
     * @param id id of the Image to found.
     * @throws IllegalStateException if the database doesn't have an image with that id.
     */
    public void checkValidImage(Long id){
        Optional<Image> imageOptional = imageRepository.findById(id);
        if(!imageOptional.isPresent()){
            throw new IllegalStateException(String.format("The Image with id %s doesn't exist.", id));
        }
    }

}
