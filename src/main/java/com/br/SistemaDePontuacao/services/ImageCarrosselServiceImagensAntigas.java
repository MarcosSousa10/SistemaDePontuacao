    
package com.br.SistemaDePontuacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.SistemaDePontuacao.Model.auth.Image;
import com.br.SistemaDePontuacao.Model.auth.ImagemCAntigas;
import com.br.SistemaDePontuacao.Repository.auth.ImageCarrosselRepositoryImagensAntigas;


@Service
public class ImageCarrosselServiceImagensAntigas {
    private final ImageCarrosselRepositoryImagensAntigas imageRepository;

    public ImageCarrosselServiceImagensAntigas(ImageCarrosselRepositoryImagensAntigas imageRepository) {
        this.imageRepository = imageRepository;
    }

public ImagemCAntigas saveImage(byte[] file) {
        ImagemCAntigas image = new ImagemCAntigas();
        image.setData(file);
        return imageRepository.save(image);
    }
    public ImagemCAntigas getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    public List<ImagemCAntigas> getAllImages() {
        return imageRepository.findAll();
    }
    public void deleteImageById() {
        imageRepository.deleteImagem();
    }
    
}