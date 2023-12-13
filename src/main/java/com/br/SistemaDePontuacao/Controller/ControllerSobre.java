package com.br.SistemaDePontuacao.Controller;

import com.br.SistemaDePontuacao.Model.app.pcprofissional;
import com.br.SistemaDePontuacao.Model.auth.campanha;
import com.br.SistemaDePontuacao.Model.auth.sobre;

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
public class ControllerSobre {
// CREATE TABLE campanha(id bigint(20) NOT NULL PRIMARY KEY,nome varchar(255),periodo varchar(255))
private Integer id;
private String texto;
  public sobre toModel(){
        return new sobre(id, texto);
    }
    
    public static ControllerSobre fromModel(sobre campanha){
        return new ControllerSobre(campanha.getId(),campanha.getTexto());
    }
}