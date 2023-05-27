package com.booking.project.image;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "images")
@Builder
public class Image {
    @Id
    @SequenceGenerator(
            name = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "image_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    @Column(name = "filename")
    private String fileName;
    private String type;
    @Lob
    @Column(name = "imagedata")
    private byte[] imageData;

    public Image(Long id, String fileName, String type, byte[] imageData) {
        this.id = id;
        this.fileName = fileName;
        this.type = type;
        this.imageData = imageData;
    }

    public Image() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", type='" + type + '\'' +
                ", imageData=" +
                '}';
    }
}
