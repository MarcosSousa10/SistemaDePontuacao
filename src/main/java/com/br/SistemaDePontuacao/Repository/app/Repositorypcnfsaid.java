package com.br.SistemaDePontuacao.Repository.app;

import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.antlr.v4.runtime.atn.SemanticContext.OR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.app.pcnfsaid;
import com.br.SistemaDePontuacao.Model.app.vendasProfissional;

public interface Repositorypcnfsaid extends JpaRepository<pcnfsaid, Long> {
    @Query(value = "SELECT p.codprofissional, "+
      "nvl((select ROUND((SUM(PUNIT * QT) / ?1)) AS codbrinde from PCMOV , PCNFSAID "+
        "where PCNFSAID.codprofissional = p.codprofissional AND PCNFSAID.NUMTRANSVENDA = PCMOV.NUMTRANSVENDA AND PCMOV.CODOPER = 'S' AND "+
         "PCNFSAID.CODFILIAL = PCMOV.CODFILIAL AND PCMOV.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) "+
         "and DTMOV between TO_DATE(?2, 'DD/MM/YYYY') and TO_DATE(?3, 'DD/MM/YYYY')),0) as pontuacao "+
            "FROM pcprofissional p "+
            "WHERE p.codprofissional= ?4 AND "+
            "(UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESING%') "+
            "GROUP BY p.codprofissional", nativeQuery = true)
    pcnfsaid pontuacao(Long fatordivisao,String dtinicio,String dtfim,String cod );
}
