package com.br.SistemaDePontuacao.Model.app;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Transient;
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
@SequenceGenerator(name = "NEXTVAL", sequenceName = "DFSEQ_PCPROFISSIONAL.NEXTVAL", allocationSize = 1)
@Table(name = "pcprofissional")
public class pcprofissionals {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NEXTVAL")
    private Integer codprofissional;
    @NotBlank
    private String descricao;
    @NotBlank
    private String senha;
    private Integer codbrinde;
    private String percomprof;
    private String tiposorteio;
    private String tipoprof;
    private Long codfunccad;
    private String profissao;
    @NotBlank
    private String cnpj;
    private String rg_ie;
    private String endereco;
    private String bairro;
    private String cep;
    private String fone;
    @NotBlank
    private String email;
    private String cidade;
    private String uf;    
    @NotBlank
    private String celular;

}
