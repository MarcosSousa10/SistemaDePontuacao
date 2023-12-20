package com.br.SistemaDePontuacao.Repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.br.SistemaDePontuacao.Model.auth.ImagemCAntigas;


public interface ImageCarrosselRepositoryImagensAntigas extends JpaRepository<ImagemCAntigas, Long> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "TRUNCATE TABLE imagemcantigas", nativeQuery = true)
    Integer deleteImagem();
}

