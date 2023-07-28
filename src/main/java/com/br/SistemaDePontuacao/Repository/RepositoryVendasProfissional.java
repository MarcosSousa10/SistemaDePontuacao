package com.br.SistemaDePontuacao.Repository;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.vendasProfissional;

public interface RepositoryVendasProfissional extends JpaRepository<vendasProfissional, Long> {
    @Query(value = "select d.numnota, d.vltotal, d.codcli, t.cliente , d.dtsaida " +
            "from pcclient t, pcnfsaid d " +
            "where d.codprofissional = ?1 and " +
            "d.codfilial in (1,4) and " +
            "d.codcli = t.codcli and " +
            "d.dtsaida BETWEEN TO_DATE(?2, 'DD/MM/YYYY') AND TO_DATE(?3, 'DD/MM/YYYY')", nativeQuery = true)
    List<vendasProfissional> select(String profissional, String dtinicio, String dtfim);
}
