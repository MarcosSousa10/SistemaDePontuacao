package com.br.SistemaDePontuacao.Model.auth;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "imagemcantigas")
public class ImagemCAntigas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "ImagemC [id=" + id + ", data=" + Arrays.toString(data) + "]";
    }
    public ImagemCAntigas(Long id, byte[] data) {
        this.id = id;
        this.data = data;
    }
    public ImagemCAntigas() {
    }


    // CREATE TABLE `imagemcantigas` (
    //     `id` bigint(20) NOT NULL PRIMARY KEY ,
    //     `data` longblob DEFAULT NULL
    //   )
 

}
