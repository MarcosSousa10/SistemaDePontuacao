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
import com.br.SistemaDePontuacao.Model.auth.ImagemC;
import com.br.SistemaDePontuacao.services.ImageCarrosselService;

@RestController
    @CrossOrigin("*")

public class ImagemCarrossel {
    private final ImageCarrosselService imageService;

    public ImagemCarrossel(ImageCarrosselService imageService) {
        this.imageService = imageService;
    }
@GetMapping("/imagemCarrossel")
    public ResponseEntity<List<String>> getImageUrls() {
        List<ImagemC> images = imageService.getAllImages();
        List<String> imageUrls = new ArrayList<>();
        for (ImagemC image : images) {
            // Assuming you have a base URL where the images are served from
            String imageUrl = "https://othondecarvalho.com.br:5555/imagemCarrossel/" + image.getId();
            imageUrls.add(imageUrl);
        }

        return ResponseEntity.ok().body(imageUrls);
    }
    
    @GetMapping("/imagemCarrossel/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        ImagemC image = imageService.getImageById(id);
        byte[] imageData = image.getData();
        String descricao = image.getDescricao();
        System.out.println(descricao+"dasd");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) 
                .body(imageData);
    }
     @PostMapping("/imagemCarrossel")
     public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                               @RequestParam("descricao") String descricao) {
         try {
             byte[] imageData = file.getBytes();
             ImagemC savedImage = imageService.saveImage(imageData, descricao);
             return ResponseEntity.ok().body("Image saved with ID: " + savedImage.getId());
         } catch (IOException e) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving image");
         }
     }
     @DeleteMapping("/imagemCarrossel")
    public ResponseEntity<String> deleteImage(@RequestParam("descricao") String descricao) {
        try {
            // Call the ImageService to delete the image by ID
            imageService.deleteImageById(descricao);
            return ResponseEntity.ok().body("Image deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image.");
        }
    }
}


