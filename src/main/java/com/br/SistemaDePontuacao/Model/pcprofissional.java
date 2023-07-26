package com.br.SistemaDePontuacao.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class pcprofissional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codprofissional;
    private String Descricao;
    private String senha;
    private String percomprof;
    private String tiposorteio;
    private String tipoprof;
    private String dtcadastro;
    private Long codfunccad;
    private String profissao;
    @NotBlank
    private String cnpj;
    private String rg_ie;
    private String endereco;
    private String bairro;
    private String cep;
    @NotBlank
    private String fone;
    @NotBlank
    private String email;
    private String cidade;
    private String uf;
    private String celular;
    private String dtnasc;


}
