package com.br.SistemaDePontuacao.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.pcprofissional;
import com.br.SistemaDePontuacao.Model.vendasProfissional;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;

public interface Repositorypcprofissional extends JpaRepository<pcprofissional,Integer>{
    @Query(value = "select a.senha,email,percomprof,uf,dtnasc,tiposorteio,rg_ie,tipoprof,codfunccad,fone,profissao,a.codprofissional,a.bairro,a.celular,a.cep,a.cidade,a.descricao,a.dtcadastro,a.endereco,a.cnpj from pcprofissional a where a.codprofissional=10509", nativeQuery = true)
    Optional<pcprofissional> informaçoesProfissional();
    
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "INSERT INTO pcprofissional (codprofissional," + 
            " senha, email, percomprof, uf, dtnasc, tiposorteio, rg_ie," +
            " tipoprof, codfunccad, fone, profissao," +
            " bairro, celular, cep, cidade, descricao, dtcadastro, endereco, cnpj" +
            ") VALUES (DFSEQ_PCPROFISSIONAL.NEXTVAL," + 
            "    '123', 'marcos@example.com', '2', 'MG', TO_DATE('1990-01-01', 'YYYY-MM-DD')," + 
            " 'C', '123456789', 'PC', '31433', '1234567890'," + 
            " 'DESIGNER', 'Barrio', '1234567890', '12345-678', 'Santa Luzia'," + 
            " 'MARCOS PEGO DE SOUSA', TO_DATE('2023-07-27', 'YYYY-MM-DD'), 'Direcci00F3n del profesional', '12.345.678/0001-90'" + 
            ")" + 
            "", nativeQuery = true)
    void fetchData();
    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update pcprofissional set email = ?1,senha = ?2 , percomprof= '2' , uf=?3, dtnasc=TO_DATE(?4, 'YYYY-MM-DD'), tiposorteio='C', rg_ie=?5," +
            " tipoprof='PC', codfunccad='31433', fone=?6, profissao=?7,bairro=?8,celular=?9,cep=?10,cidade=?11,descricao=?12,dtcadastro=TO_DATE(?13, 'YYYY-MM-DD'),endereco=?14,cnpj=?15 where codprofissional =16837", nativeQuery = true)
    Object status(String email,String senha,String uf,String dtnasc,String rg_ie,String fone,String profissao,String Bairro,String celular,String cep,String cidade,String descricao,String dtcadastro,String endereco,String cnpj);
}
