package com.br.SistemaDePontuacao.Controller;

import com.br.SistemaDePontuacao.Model.app.pcprofissional;
import com.br.SistemaDePontuacao.Model.auth.campanha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerCampanha {
// CREATE TABLE campanha(id bigint(20) NOT NULL PRIMARY KEY,nome varchar(255),periodo varchar(255))
private Long id;
private String nome;
private String periodo; 
  public campanha toModel(){
        return new campanha(id, nome, periodo);
    }
    
    public static ControllerCampanha fromModel(campanha campanha){
        return new ControllerCampanha(campanha.getId(),campanha.getNome(),campanha.getPeriodo());
    }
}