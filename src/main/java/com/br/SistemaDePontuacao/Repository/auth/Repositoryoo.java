package com.br.SistemaDePontuacao.Repository.auth;


import java.util.List;
import java.util.Optional;

import javax.print.DocFlavor.STRING;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.br.SistemaDePontuacao.Model.app.vendasProfissional;
import com.br.SistemaDePontuacao.Model.auth.Produto;



public interface Repositoryoo extends CrudRepository<Produto,Long>{
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE parametros SET fatordivisao = ?1", nativeQuery = true)
    Integer fatordivisao(String fatordivisao);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE parametros SET dtfim = ?1", nativeQuery = true)
    Integer fim(String dtfim);
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE parametros SET dtinicio = ?1", nativeQuery = true)
    Integer inicio(String dtinicio);


@Query(value = " SELECT * FROM parametros", nativeQuery = true)
    Optional<Produto> select();
}
