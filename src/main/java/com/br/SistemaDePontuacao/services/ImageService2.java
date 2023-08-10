package com.br.SistemaDePontuacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.SistemaDePontuacao.Model.auth.Image2;
import com.br.SistemaDePontuacao.Repository.auth.ImageRepository2;


@Service
public class ImageService2 {
    private final ImageRepository2 imageRepository;

    public ImageService2(ImageRepository2 imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image2 saveImage(byte[] imageData) {
        Image2 image = new Image2();
        image.setData(imageData);
        return imageRepository.save(image);
    }

    public Image2 getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    public List<Image2> getAllImages() {
        return imageRepository.findAll();
    }
    public void deleteImageById() {
        imageRepository.deleteAll();
    }
    
}