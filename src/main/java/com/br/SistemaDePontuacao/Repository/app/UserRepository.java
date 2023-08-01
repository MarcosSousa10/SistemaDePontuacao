package com.br.SistemaDePontuacao.Repository.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.SistemaDePontuacao.Model.app.user.User;



public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByLogin(String login);
}
