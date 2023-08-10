package com.br.SistemaDePontuacao.Controller;

import com.br.SistemaDePontuacao.Model.auth.Produto;
import com.br.SistemaDePontuacao.Model.auth.video;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class videoRequest {
    private Long id;
    private String url;
            public video toModel(){
        return new video(id,url);
    }
    
    public static videoRequest fromModel(video logins){
        return new videoRequest(logins.getId(),logins.getUrl());
    }
 
}
