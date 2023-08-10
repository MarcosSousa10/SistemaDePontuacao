package com.br.SistemaDePontuacao.Model.auth;

import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Image1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(length=512)
    private byte[] data;

    public Long getId() {
        return id;
    }
    // CREATE TABLE `image` (
    //     `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    //     `data` longblob DEFAULT NULL
    //   )
    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Image1() {
    }

    public Image1(Long id, byte[] data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Image [id=" + id + ", data=" + Arrays.toString(data) + "]";
    }

}
