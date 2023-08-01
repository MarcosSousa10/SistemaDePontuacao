package com.br.SistemaDePontuacao.Repository.app;

import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.app.pcnfsaid;
import com.br.SistemaDePontuacao.Model.app.vendasProfissional;

public interface Repositorypcnfsaid extends JpaRepository<pcnfsaid, Long> {
    @Query(value = "SELECT a.codprofissional, SUM(a.vltotal) / ?1 AS PONTUACAO " +
            "FROM PCNFSAID a " +
            "WHERE a.codprofissional=?2 and DTSAIDA BETWEEN TO_DATE(?3, 'DD/MM/YYYY') AND TO_DATE(?4, 'DD/MM/YYYY') " +
            "GROUP BY a.codprofissional", nativeQuery = true)
    pcnfsaid pontuacao(Long fatordivisao,String cod,String dtinicio,String dtfim );
}
