package com.br.SistemaDePontuacao.Repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.SistemaDePontuacao.Model.auth.campanha;

public interface RepositoryCampanha extends JpaRepository<campanha,Long> {
    
}
