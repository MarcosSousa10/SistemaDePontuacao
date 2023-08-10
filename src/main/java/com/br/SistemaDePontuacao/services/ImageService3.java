package com.br.SistemaDePontuacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.SistemaDePontuacao.Model.auth.Image3;
import com.br.SistemaDePontuacao.Repository.auth.ImageRepository3;


@Service
public class ImageService3 {
    private final ImageRepository3 imageRepository;

    public ImageService3(ImageRepository3 imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image3 saveImage(byte[] imageData) {
        Image3 image = new Image3();
        image.setData(imageData);
        return imageRepository.save(image);
    }

    public Image3 getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    public List<Image3> getAllImages() {
        return imageRepository.findAll();
    }
    public void deleteImageById() {
        imageRepository.deleteAll();
    }
    
}