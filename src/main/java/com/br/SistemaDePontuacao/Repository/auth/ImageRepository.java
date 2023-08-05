package com.br.SistemaDePontuacao.Repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.SistemaDePontuacao.Model.auth.Image;


public interface ImageRepository extends JpaRepository<Image, Long> {
}
