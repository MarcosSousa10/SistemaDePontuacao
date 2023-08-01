package com.br.SistemaDePontuacao.Controller;

import com.br.SistemaDePontuacao.Model.auth.Produto;

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
public class ParametrosRequest {
    private Long id;
    private String dtfim;
    private String dtinicio;    
    private Long fatordivisao;
        public Produto toModel(){
        return new Produto(id,dtfim,dtinicio,fatordivisao);
    }
    
    public static ParametrosRequest fromModel(Produto logins){
        return new ParametrosRequest(logins.getId(),logins.getDtfim(),logins.getDtinicio(),logins.getFatordivisao());
    }
 
}

