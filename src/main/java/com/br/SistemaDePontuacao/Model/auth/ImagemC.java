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
@Table(name = "imagemc")
public class ImagemC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @Lob
    @Column(length=512)
    private byte[] data;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "ImagemC [id=" + id + ", descricao=" + descricao + ", data=" + Arrays.toString(data) + "]";
    }
    public ImagemC(Long id, String descricao, byte[] data) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
    }
    public ImagemC() {
    }


    // CREATE TABLE `ImagemC` (
    //     `id` bigint(20) NOT NULL PRIMARY KEY ,
    //     `data` longblob DEFAULT NULL
    //   )
 

}
