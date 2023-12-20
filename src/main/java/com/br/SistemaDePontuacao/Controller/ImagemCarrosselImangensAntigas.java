package com.br.SistemaDePontuacao.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.br.SistemaDePontuacao.Model.auth.Image;
import com.br.SistemaDePontuacao.Model.auth.ImagemCAntigas;
import com.br.SistemaDePontuacao.services.ImageCarrosselServiceImagensAntigas;

@RestController
    @CrossOrigin("*")

public class ImagemCarrosselImangensAntigas {
    private final ImageCarrosselServiceImagensAntigas imageService;

    public ImagemCarrosselImangensAntigas(ImageCarrosselServiceImagensAntigas imageService) {
        this.imageService = imageService;
    }
@GetMapping("/imagemCarrosselFAntigas")
    public ResponseEntity<List<String>> getImageUrls() {
        List<ImagemCAntigas> images = imageService.getAllImages();
        List<String> imageUrls = new ArrayList<>();
        for (ImagemCAntigas image : images) {
            // Assuming you have a base URL where the images are served from
            String imageUrl = "https://othondecarvalho.com.br:5555/imagemCarrosselFAntigas/" + image.getId();
            imageUrls.add(imageUrl);
        }

        return ResponseEntity.ok().body(imageUrls);
    }
    
    @GetMapping("/imagemCarrosselFAntigas/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ImagemCAntigas image = imageService.getImageById(id);
        byte[] imageData = image.getData();
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) 
                .body(imageData);
    }
     @PostMapping("/imagemCarrosselFAntigas")
     public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
         try {
             byte[] imageData = file.getBytes();
             ImagemCAntigas savedImage = imageService.saveImage(imageData);
             return ResponseEntity.ok().body("Image saved with ID: " + savedImage.getId());
         } catch (IOException e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
         }
     }
     @DeleteMapping("/imagemCarrosselFAntigas")
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


