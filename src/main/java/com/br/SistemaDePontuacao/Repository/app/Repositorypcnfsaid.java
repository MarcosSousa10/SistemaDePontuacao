package com.br.SistemaDePontuacao.Repository.app;

import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.app.pcnfsaid;
import com.br.SistemaDePontuacao.Model.app.vendasProfissional;

public interface Repositorypcnfsaid extends JpaRepository<pcnfsaid, Long> {
    @Query(value = "SELECT p.codprofissional, "+
     " nvl((select SUM(vltotal) / ?1 AS codbrinde from pcnfsaid where codprofissional = p.codprofissional and dtsaida between TO_DATE(?2, 'DD/MM/YYYY') and TO_DATE(?3, 'DD/MM/YYYY')),0) as pontuacao " + 
            "FROM pcprofissional p " +
            "WHERE p.codprofissional=?4 " +
            "GROUP BY p.codprofissional", nativeQuery = true)
    pcnfsaid pontuacao(Long fatordivisao,String dtinicio,String dtfim,String cod );
}
