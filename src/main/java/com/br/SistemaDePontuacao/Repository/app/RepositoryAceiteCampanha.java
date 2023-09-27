package com.br.SistemaDePontuacao.Repository.app;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.app.aceitarCampanha;
import com.br.SistemaDePontuacao.Model.app.pcprofissional;

import jakarta.transaction.Transactional;

public interface RepositoryAceiteCampanha extends JpaRepository<aceitarCampanha,Long>{
      @Query(value = "select aceite_campanha, codprofissional from pcprofissional where aceite_campanha = '1' and codprofissional = ?1", nativeQuery = true)
  Optional<aceitarCampanha> aceitarCampanha(String cod);
    @Transactional
  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Query(value = "update pcprofissional set aceite_campanha = '1' where codprofissional =?1", nativeQuery = true)
  Object UpdataTermos(String codprofissional);
}
