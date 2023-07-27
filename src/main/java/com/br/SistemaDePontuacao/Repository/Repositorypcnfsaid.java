package com.br.SistemaDePontuacao.Repository;

import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.pcnfsaid;
import com.br.SistemaDePontuacao.Model.vendasProfissional;

public interface Repositorypcnfsaid extends JpaRepository<pcnfsaid, Long> {
    @Query(value = "SELECT a.codprofissional, SUM(a.vltotal) / 1000 AS PONTUACAO " +
            "FROM PCNFSAID a " +
            "WHERE a.codprofissional=6022 and DTSAIDA BETWEEN TO_DATE('17-01-2021', 'DD/MM/YYYY') AND TO_DATE('17-01-2022', 'DD/MM/YYYY') " +
            "GROUP BY a.codprofissional", nativeQuery = true)
    Optional<pcnfsaid> pontuacao();
}
