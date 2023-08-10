package com.br.SistemaDePontuacao.Controller;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.br.SistemaDePontuacao.Model.auth.Image2;
import com.br.SistemaDePontuacao.services.ImageService2;

    @RestController
    @CrossOrigin("*")

public class ImageController2 {
    private final ImageService2 imageService;

    public ImageController2(ImageService2 imageService) {
        this.imageService = imageService;
    }
@GetMapping("/images2")
    public ResponseEntity<List<String>> getImageUrls() {
        List<Image2> images = imageService.getAllImages();
        List<String> imageUrls = new ArrayList<>();

        for (Image2 image : images) {
            // Assuming you have a base URL where the images are served from
            String imageUrl = "http://192.168.2.181:5555/images2/" + image.getId();
            imageUrls.add(imageUrl);
        }

        return ResponseEntity.ok().body(imageUrls);
    }
    
    @GetMapping("/images2/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Image2 image = imageService.getImageById(id);
        byte[] imageData = image.getData();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) 
                .body(imageData);
    }
    @PostMapping("/images2")
    public ResponseEntity<String> uploadImage(@RequestBody MultipartFile file) {
        try {
            byte[] imageData = file.getBytes();
            Image2 savedImage = imageService.saveImage(imageData);
            return ResponseEntity.ok().body("Image saved with ID: " + savedImage.getId());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
        }
    }
     @DeleteMapping("/images2")
    public ResponseEntity<String> deleteImage() {
        try {
            // Call the ImageService to delete the image by ID
            imageService.deleteImageById();
            return ResponseEntity.ok().body("Image deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image.");
        }
    }
}


