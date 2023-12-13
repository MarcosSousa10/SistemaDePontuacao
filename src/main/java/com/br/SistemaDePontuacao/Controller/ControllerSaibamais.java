package com.br.SistemaDePontuacao.Controller;

import com.br.SistemaDePontuacao.Model.app.pcprofissional;
import com.br.SistemaDePontuacao.Model.auth.campanha;
import com.br.SistemaDePontuacao.Model.auth.saibamais;

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
public class ControllerSaibamais {
// CREATE TABLE campanha(id bigint(20) NOT NULL PRIMARY KEY,nome varchar(255),periodo varchar(255))
private Integer id;
private String texto;
  public saibamais toModel(){
        return new saibamais(id, texto);
    }
    
    public static ControllerSaibamais fromModel(saibamais saiba){
        return new ControllerSaibamais(saiba.getId(),saiba.getTexto());
    }
}