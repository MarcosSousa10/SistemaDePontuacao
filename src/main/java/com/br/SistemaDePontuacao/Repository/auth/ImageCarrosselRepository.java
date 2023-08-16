package com.br.SistemaDePontuacao.Repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.br.SistemaDePontuacao.Model.auth.ImagemC;


public interface ImageCarrosselRepository extends JpaRepository<ImagemC, Long> {
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE from imagemc where descricao=?1 ", nativeQuery = true)
    Integer deleteImagem(String descricao);
}

