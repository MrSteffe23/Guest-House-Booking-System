package com.booking.project.image;

import com.booking.project.house.HouseService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the closest point from the user. It defines the end points of the
 * application and lets the {@link ImageService} to deal with the logic behind this operations.<br>
 * All requests from the users are coming here first.
 */
@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final IImageService imageService;

    /**
     * Constructor which have the role to implement Dependency Injection for the imageService attribute.
     * @param imageService the reference to the Service layer.
     */
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return a list with all the Images in the database.
     */
    @GetMapping("/images")
    public List<ResponseEntity<byte[]>> getAllImages() {
        List<Image> images = imageService.getAllImages();
        /*for(Image image: images){
            System.out.println(image);
        }*/
        List<ResponseEntity<byte[]>> responseEntities = new ArrayList<>();
        for (Image image : images) {
            if (image != null) {
                ResponseEntity<byte[]> responseEntity = ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your images
                        .body(image.getImageData());
                responseEntities.add(responseEntity);
            } else {
                responseEntities.add(ResponseEntity.notFound().build());
            }
        }
        return responseEntities;
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return an Image from the database found by an id.
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<byte[]> getImageById(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your images
                    .body(image.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return an Image from the database found by a type.
     */
    @GetMapping("/type/{type}/{number}")
    public ResponseEntity<byte[]> getImageByType(@PathVariable int number, @PathVariable String type) {
        Image image = imageService.getImageByType(type, number);
        if (image != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your images
                    .body(image.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return an Image from the database found by a type and a number;
     */
    @GetMapping("/typeId/{type}/{number}")
    public Image getImageByTypeForId(@PathVariable int number, @PathVariable String type) {
        return imageService.getImageByType(type, number);
    }

    /**
     * This method is used for display purposes, or for debugging.
     * @return A list with Images from the database found by a type.
     */
    @GetMapping("/type/{type}")
    public List<ResponseEntity<byte[]>> getImagesByType(@PathVariable String type){
        List<Image> images = imageService.getImagesByType(type);
        List<ResponseEntity<byte[]>> responseEntities = new ArrayList<>();
        for (Image image : images) {
            if (image != null) {
                ResponseEntity<byte[]> responseEntity = ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Set the appropriate content type for your images
                        .body(image.getImageData());
                responseEntities.add(responseEntity);
            } else {
                responseEntities.add(ResponseEntity.notFound().build());
            }
        }
        return responseEntities;
    }

    /**
     * Inserts a new Image in the database, if possible.
     * @param file the image
     * @param type the type of the image
     */
    @PostMapping("/{type}")
    public Image createImage(@RequestParam("image")MultipartFile file,@PathVariable String type) throws IOException {
        return imageService.createImage(file, type);
    }

    /**
     * Deletes an Image from the database, if possible.
     * @param id the id of the Image to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);
    }
}
