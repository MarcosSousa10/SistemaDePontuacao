package com.br.SistemaDePontuacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.SistemaDePontuacao.Model.auth.Image1;
import com.br.SistemaDePontuacao.Repository.auth.ImageRepository1;


@Service
public class ImageService1 {
    private final ImageRepository1 imageRepository;

    public ImageService1(ImageRepository1 imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image1 saveImage(byte[] imageData) {
        Image1 image = new Image1();
        image.setData(imageData);
        return imageRepository.save(image);
    }

    public Image1 getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    public List<Image1> getAllImages() {
        return imageRepository.findAll();
    }
    public void deleteImageById() {
        imageRepository.deleteAll();
    }
    
}