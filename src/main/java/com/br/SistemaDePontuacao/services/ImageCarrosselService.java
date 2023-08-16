    
package com.br.SistemaDePontuacao.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.br.SistemaDePontuacao.Model.auth.Image;
import com.br.SistemaDePontuacao.Model.auth.ImagemC;
import com.br.SistemaDePontuacao.Repository.auth.ImageCarrosselRepository;


@Service
public class ImageCarrosselService {
    private final ImageCarrosselRepository imageRepository;

    public ImageCarrosselService(ImageCarrosselRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

public ImagemC saveImage(byte[] file, String descricao) {
        ImagemC image = new ImagemC();
        image.setData(file);
        // Aquí deberías establecer las propiedades 'datas' y 'descricao' en el objeto 'image'
        image.setDescricao(descricao);
        return imageRepository.save(image);
    }
    public ImagemC getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }
    public List<ImagemC> getAllImages() {
        return imageRepository.findAll();
    }
    public void deleteImageById(String descricao) {
        imageRepository.deleteImagem(descricao);
    }
    
}