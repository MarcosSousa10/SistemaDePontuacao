package com.br.SistemaDePontuacao.Repository.app;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.app.pcprofissional;
import com.br.SistemaDePontuacao.Model.app.vendasProfissional;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;

public interface Repositorypcprofissional extends JpaRepository<pcprofissional, Integer> {
    @Query(value = "SELECT p.codprofissional," +
            " nvl((select SUM(vltotal) / ?1 AS codbrinde from pcnfsaid where codprofissional = p.codprofissional),0) as codbrinde,"+
            " (select max(dtsaida) from pcnfsaid where codprofissional = ?2 ) as tipoprof," +
            " p.descricao, " +
            " p.percomprof, " +
            " p.tiposorteio, " +
            " p.senha, " +
            " p.dtcadastro, " +
            " p.codfunccad, " +
            " p.profissao, " +
            " p.cnpj, " +
            " p.rg_ie, " +
            " p.endereco, " +
            " p.bairro, " +
            " p.cep, " +
            " p.fone, " +
            " p.email, " +
            " p.cidade, " +
            " p.uf, " +
            " p.celular, " +
            " p.dtnasc " +
            "FROM pcprofissional p  " +
            "where p.codprofissional=?3 " +
            "GROUP BY p.codprofissional, p.descricao, p.percomprof, p.tiposorteio, p.senha, p.dtcadastro, p.codfunccad, p.profissao, p.cnpj, p.rg_ie, p.endereco, p.bairro, p.cep, p.fone, p.email, p.cidade, p.uf, p.celular, p.dtnasc  order by codbrinde DESC ", nativeQuery = true)
    Optional<pcprofissional> informaçoesProfissional(Long fator, String cod, String cods);

    @Query(value = "SELECT p.codprofissional, " +
            "nvl((select SUM(vltotal) / ?1 AS codbrinde from pcnfsaid where codprofissional = p.codprofissional),0) as codbrinde,"+
            "  p.descricao," +
            "  p.senha," +
            "  p.percomprof," +
            " p.tiposorteio," +
            "  p.tipoprof, " +
            "  p.dtcadastro," +
            " p.codfunccad," +
            " p.profissao," +
            " p.cnpj," +
            " p.rg_ie," +
            " p.endereco," +
            " p.bairro," +
            " p.cep," +
            " p.fone," +
            " p.email," +
            " p.cidade," +
            " p.uf," +
            " p.celular," +
            " p.dtnasc" +
            " FROM pcprofissional p" +
            " where p.cnpj = ?2" +
            " GROUP BY p.codprofissional, p.descricao, p.senha, p.percomprof, p.tiposorteio, p.tipoprof, p.dtcadastro, p.codfunccad, p.profissao, p.cnpj, p.rg_ie, p.endereco, p.bairro, p.cep, p.fone, p.email, p.cidade, p.uf, p.celular, p.dtnasc  order by codbrinde DESC", nativeQuery = true)
    Optional<pcprofissional> codprof(Long fatordivisao, String cnpj);

    @Query(value = "SELECT p.codprofissional, " +
  "nvl((select SUM(vltotal) / ?1 AS codbrinde from pcnfsaid where codprofissional = p.codprofissional and dtsaida between TO_DATE(?2, 'DD/MM/YYYY') and TO_DATE(?3, 'DD/MM/YYYY')),0) as codbrinde, "+
            "  p.senha," +
            "p.descricao,"+
            "  p.percomprof," +
            " p.tiposorteio," +
            "  p.tipoprof, " +
            "  p.dtcadastro," +
            " p.codfunccad," +
            " p.profissao," +
            " p.cnpj," +
            " p.rg_ie," +
            " p.endereco," +
            " p.bairro," +
            " p.cep," +
            " p.fone," +
            " p.email," +
            " p.cidade," +
            " p.uf," +
            " p.celular," +
            " TO_CHAR(p.dtnasc , 'DD/MM/YYYY') as dtnasc" +
            " FROM pcprofissional p" +
            " where p.cnpj = ?4" +
            " GROUP BY p.codprofissional, p.descricao, p.senha, p.percomprof, p.tiposorteio, p.tipoprof, p.dtcadastro, p.codfunccad, p.profissao, p.cnpj, p.rg_ie, p.endereco, p.bairro, p.cep, p.fone, p.email, p.cidade, p.uf, p.celular, p.dtnasc  order by codbrinde DESC", nativeQuery = true)
    Optional<pcprofissional> informacaofiltrocnpj(Long fatordivisao,String dtinico,String dtsaida,  String cnpj );

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "INSERT INTO pcprofissional (codprofissional," +
            " senha, email, percomprof, uf, dtnasc, tiposorteio, rg_ie," +
            " tipoprof, codfunccad, fone, profissao," +
            " bairro, celular, cep, cidade, descricao, dtcadastro, endereco, cnpj" +
            ") VALUES (DFSEQ_PCPROFISSIONAL.NEXTVAL," +
            "    ?1, ?2, '2', ?3, TO_DATE(?4, 'DD-MM-YYYY'),'C', ?5," +
            "  'PC', '31433', ?6,?7," +
            " ?8,?9, ?10, ?11," +
            " ?12, TO_DATE(SYSDATE, 'DD-MM-YYYY'), ?13, ?14" +
            ")" +
            "", nativeQuery = true)
    Object salvar(String senha, String email, String uf, String dtnasc, String rg_ie, String fone, String profissao,
            String bairro, String celular, String cep, String cidade, String descricao, String endereco, String cnpj);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "update pcprofissional set email = ?1,senha=?2,celular=?3," +
            "descricao=?4,uf=?5,rg_ie=?6,fone=?7,profissao=?8,bairro=?9,cep=?10,cidade=?11," +
            "endereco=?12,cnpj=?13 where codprofissional =?15", nativeQuery = true)
    Object status(String email, String senha, String celular, String descricao, String uf, String rg_ie,
            String fone, String profissao, String bairro, String cep, String cidade, String endereco, String cnpj,
            String codprofissional);

    @Query(value = "SELECT a.codprofissional, " +
            " SUM(a.vltotal) / ?1 AS codbrinde," +
            " p.descricao, " +
            " p.senha, " +
            " p.percomprof, " +
            " p.tiposorteio, " +
            " p.tipoprof, " +
            " p.dtcadastro, " +
            " p.codfunccad, " +
            " p.profissao, " +
            " p.cnpj, " +
            " p.rg_ie, " +
            " p.endereco, " +
            " p.bairro, " +
            " p.cep, " +
            " p.fone, " +
            " p.email, " +
            " p.cidade, " +
            " p.uf, " +
            " p.celular, " +
            " p.dtnasc " +
            "FROM PCNFSAID a " +
            "JOIN pcprofissional p ON a.codprofissional = p.codprofissional " +
            "WHERE DTSAIDA BETWEEN TO_DATE(?2, 'DD/MM/YYYY') AND TO_DATE(?3, 'DD/MM/YYYY') " +
            "GROUP BY a.codprofissional, p.descricao, p.senha, p.percomprof, p.tiposorteio, p.tipoprof, p.dtcadastro, p.codfunccad, p.profissao, p.cnpj, p.rg_ie, p.endereco, p.bairro, p.cep, p.fone, p.email, p.cidade, p.uf, p.celular, p.dtnasc  order by codbrinde DESC", nativeQuery = true)
    List<pcprofissional> SELECT(Long fatordivisao, String dtinico, String dtfim);

    @Query(value = "SELECT a.codprofissional, " +
            " SUM(a.vltotal) / ?1 AS codbrinde," +
            " p.descricao, " +
            " p.senha, " +
            " p.percomprof, " +
            " p.tiposorteio, " +
            " p.tipoprof, " +
            " p.dtcadastro, " +
            " p.codfunccad, " +
            " p.profissao, " +
            " p.cnpj, " +
            " p.rg_ie, " +
            " p.endereco, " +
            " p.bairro, " +
            " p.cep, " +
            " p.fone, " +
            " p.email, " +
            " p.cidade, " +
            " p.uf, " +
            " p.celular, " +
            " p.dtnasc " +
            "FROM PCNFSAID a " +
            "JOIN pcprofissional p ON a.codprofissional = p.codprofissional " +
            "WHERE p.cnpj=?2 " +
            "GROUP BY a.codprofissional, p.descricao, p.senha, p.percomprof, p.tiposorteio, p.tipoprof, p.dtcadastro, p.codfunccad, p.profissao, p.cnpj, p.rg_ie, p.endereco, p.bairro, p.cep, p.fone, p.email, p.cidade, p.uf, p.celular, p.dtnasc  order by codbrinde DESC", nativeQuery = true)
    List<pcprofissional> filtro(Long fatordivisao, String cnpj);

}
