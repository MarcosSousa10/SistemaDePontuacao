package com.br.SistemaDePontuacao.Repository.app;

import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.antlr.v4.runtime.atn.SemanticContext.OR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.br.SistemaDePontuacao.Model.app.pcprofissional;
import com.br.SistemaDePontuacao.Model.app.vendasProfissional;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;

public interface Repositorypcprofissional extends JpaRepository<pcprofissional, Integer> {
    @Query(value = "SELECT p.codprofissional, " +
    "(select NVL(SUM((a.PUNIT * A.QT) / ?1),0) from pcmov a, pcnfsaid s WHERE A.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) AND " +
     " s.codprofissional = p.codprofissional AND s.NUMTRANSVENDA = A.NUMTRANSVENDA AND A.CODOPER = 'S' AND A.CODFILIAL = s.CODFILIAL AND a.DTCANCEL IS NULL  ) AS codbrinde, "+
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
  "where p.codprofissional=?3 and" +
  "(UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESIG%') "+
                  " AND P.SITUACAO = 'A'  order by codbrinde DESC ", nativeQuery = true)
    Optional<pcprofissional> informaçoesProfissional(Long fator, String cod, String cods);

    @Query(value = " SELECT P.codprofissional,  "+
     "(select NVL(SUM((a.PUNIT * A.QT) / ?1),0) from pcmov a, pcnfsaid s WHERE A.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) AND " +
     " s.codprofissional = p.codprofissional AND s.NUMTRANSVENDA = A.NUMTRANSVENDA AND A.CODOPER = 'S' AND A.CODFILIAL = s.CODFILIAL AND a.DTCANCEL IS NULL  ) AS codbrinde, "+
     "p.descricao,  "+
     "p.senha,  "+
     "p.percomprof,  "+
     "p.tiposorteio,  "+
     "p.tipoprof,  "+
     "p.dtcadastro,  "+
     "p.codfunccad,  "+
     "p.profissao,  "+
     "p.cnpj,  "+
     "p.rg_ie,  "+
     "p.endereco,  "+
     "p.bairro,  "+
     "p.cep,  "+
     "p.fone,  "+
     "p.email,  "+
     "p.cidade,  "+
     "p.uf,  "+
     "p.celular,  "+
     "p.dtnasc  "+
     "  FROM PCPROFISSIONAL P "+
     "  where "+
     "  p.cnpj =?2 and "+
     "  (UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESIG%') "+
     "  AND P.SITUACAO = 'A'", nativeQuery = true)
    Optional<pcprofissional> codprof(Long fatordivisao, String cnpj);

        @Query(value = " SELECT "+
        " codprofissional, "+
        "   descricao, "+
        " senha, "+
        " codbrinde,"+
       " percomprof,"+
        " tiposorteio,"+
       " tipoprof,"+
        " dtcadastro,"+
        " codfunccad,"+
        " profissao,"+
        " cnpj,"+
        " rg_ie,"+
        " endereco,"+
        " bairro,"+
       " cep,"+
        " fone,"+
       " email,"+
        " cidade,"+
        " uf,"+    
       " celular,"+
        " dtnasc"+
       " FROM PCPROFISSIONAL WHERE CNPJ=?1", nativeQuery = true)
    Optional<pcprofissional> validarcnpj(String cnpj);
    @Query(value = " SELECT P.codprofissional,  "+
     "(select NVL(SUM((a.PUNIT * A.QT) / ?1),0) from pcmov a, pcnfsaid s WHERE   A.DTMOV BETWEEN TO_DATE(?2, 'DD/MM/YYYY') AND TO_DATE(?3, 'DD/MM/YYYY')  AND A.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) AND " +
     " s.codprofissional = p.codprofissional AND s.NUMTRANSVENDA = A.NUMTRANSVENDA AND A.CODOPER = 'S' AND A.CODFILIAL = s.CODFILIAL AND a.DTCANCEL IS NULL  ) AS codbrinde, "+
     "p.descricao,  "+
     "p.senha,  "+
     "p.percomprof,  "+
     "p.tiposorteio,  "+
     "p.tipoprof,  "+
     "p.dtcadastro,  "+
     "p.codfunccad,  "+
     "p.profissao,  "+
     "p.cnpj,  "+
     "p.rg_ie,  "+
     "p.endereco,  "+
     "p.bairro,  "+
     "p.cep,  "+
     "p.fone,  "+
     "p.email,  "+
     "p.cidade,  "+
     "p.uf,  "+
     "p.celular,  "+
     "p.dtnasc  "+
     "  FROM PCPROFISSIONAL P "+
     "  where "+
     "  p.cnpj =?4 and "+
     "  (UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESIG%') ", nativeQuery = true)
    Optional<pcprofissional> informacaofiltrocnpj(Long fatordivisao,String dtinico,String dtsaida,  String cnpj );

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "INSERT INTO pcprofissional (codprofissional," +
  " senha, email, percomprof, uf, dtnasc, tiposorteio, rg_ie," +
  " tipoprof, codfunccad, fone, profissao," +
  " bairro, celular, cep, cidade, descricao, dtcadastro, endereco, cnpj, situacao" +
  ") VALUES (DFSEQ_PCPROFISSIONAL.NEXTVAL," +
  "    ?1, ?2, '2', ?3, TO_DATE(?4, 'DD-MM-YYYY'),'C', ?5," +
  "  'PC', '31433', ?6,?7," +
  " ?8,?9, ?10, ?11," +
  " ?12, TO_DATE(SYSDATE, 'DD-MM-YYYY'), ?13, ?14, 'A'" +
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

    @Query(value = " SELECT P.codprofissional,  " +
                  "(select NVL(SUM((a.PUNIT * A.QT) / ?1),0) from pcmov a, pcnfsaid s WHERE A.DTMOV BETWEEN TO_DATE(?2, 'DD/MM/YYYY') AND TO_DATE(?3, 'DD/MM/YYYY')  AND A.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) AND " +
                  "s.codprofissional = p.codprofissional AND s.NUMTRANSVENDA = A.NUMTRANSVENDA AND A.CODOPER = 'S' AND A.CODFILIAL = s.CODFILIAL AND a.DTCANCEL IS NULL FETCH FIRST 10 ROWS ONLY  ) AS codbrinde, " +
                  "p.descricao, " +
                  "p.senha,  " +
                  "p.percomprof,  " +
                  "p.tiposorteio, " +
                  "p.tipoprof,  " +
                  "p.dtcadastro,  " +
                  "p.codfunccad,  " +
                  "p.profissao, " +
                  "p.cnpj,  " +
                  "p.rg_ie,  " +
                  "p.endereco, " +
                  "p.bairro, " +
                  "p.cep,  " +
                  "p.fone,  " +
                  "p.email, " +
                  "p.cidade, " +
                  "p.uf,  " +
                  "p.celular,  " +
                  "p.dtnasc  " +
                  "FROM PCPROFISSIONAL P " +
                  "where " +
                  "(UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESIG%') " +
                  "AND P.SITUACAO = 'A' ORDER BY codbrinde DESC", nativeQuery = true)
    List<pcprofissional> SELECT(Long fatordivisao, String dtinico, String dtfim);

    @Query(value = " SELECT P.codprofissional,  "+
     "(select NVL(SUM((a.PUNIT * A.QT) / ?1),0) from pcmov a, pcnfsaid s WHERE A.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) AND " +
     " s.codprofissional = p.codprofissional AND s.NUMTRANSVENDA = A.NUMTRANSVENDA AND A.CODOPER = 'S' AND A.CODFILIAL = s.CODFILIAL AND a.DTCANCEL IS NULL  ) AS codbrinde, "+
     "p.descricao,  "+
     "p.senha,  "+
     "p.percomprof,  "+
     "p.tiposorteio,  "+
     "p.tipoprof,  "+
     "p.dtcadastro,  "+
     "p.codfunccad,  "+
     "p.profissao,  "+
     "p.cnpj,  "+
     "p.rg_ie,  "+
     "p.endereco,  "+
     "p.bairro,  "+
     "p.cep,  "+
     "p.fone,  "+
     "p.email,  "+
     "p.cidade,  "+
     "p.uf,  "+
     "p.celular,  "+
     "p.dtnasc  "+
     "  FROM PCPROFISSIONAL P "+
     "  where "+
     "  p.cnpj =?2 and "+
     "  (UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESIG%')  order by codbrinde DESC", nativeQuery = true)
    List<pcprofissional> filtro(Long fatordivisao, String cnpj);
  @Query(value = "SELECT P.codprofissional,  "+
  "(select NVL(SUM((a.PUNIT * A.QT) / ?1),0) from pcmov a, pcnfsaid s WHERE extract (MONTH from dtsaida)=?2 and  A.DTMOV BETWEEN TO_DATE(?3, 'DD/MM/YYYY') AND TO_DATE(?4, 'DD/MM/YYYY')  AND A.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) AND "+
   "s.codprofissional = p.codprofissional AND s.NUMTRANSVENDA = A.NUMTRANSVENDA AND A.CODOPER = 'S' AND A.CODFILIAL = s.CODFILIAL AND a.DTCANCEL IS NULL  ) AS codbrinde, "+
  "p.descricao, "+  
  "p.senha,  "+
  "p.percomprof,  "+
  "p.tiposorteio, "+ 
  "p.tipoprof,  "+
  "p.dtcadastro,  "+
  "p.codfunccad,  "+
  "p.profissao, "+ 
  "p.cnpj,  "+
  "p.rg_ie,  "+
  "p.endereco, "+ 
  "p.bairro, "+ 
  "p.cep,  "+
  "p.fone,  "+
  "p.email, "+
  "p.cidade, "+
  "p.uf,  "+
  "p.celular,  "+
  "p.dtnasc  "+
    "FROM PCPROFISSIONAL P "+ 
    "where "+
    "p.cnpj =?5 and "+
    "(UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESIG%') "+
    "AND P.SITUACAO = 'A'", nativeQuery = true)
    Optional<pcprofissional> dashboard(Long fatordivisao,String mes, String dtinico, String dtfim, String cnpj );

  @Query(value = "SELECT P.codprofissional, "+ 
"NVL(SUM((a.PUNIT * A.QT) / ?1 ),0) AS codbrinde, "+
"p.descricao,  "+
"p.senha,  "+
"p.percomprof, "+  
"p.tiposorteio,  "+
"p.tipoprof,  "+
"p.dtcadastro,  "+
"p.codfunccad,  "+
"p.profissao,  "+
"p.cnpj,  "+
"p.rg_ie,  "+
"p.endereco, "+ 
"p.bairro,  "+
"p.cep,  "+
"p.fone,  "+
"p.email,  "+
"p.cidade,  "+
"p.uf,  "+
"p.celular, "+ 
"p.dtnasc  "+
  "FROM PCMOV a , "+
  "PCNFSAID D, PCPROFISSIONAL P "+
  "WHERE A.DTMOV BETWEEN TO_DATE(?2, 'DD/MM/YYYY') AND TO_DATE(?3, 'DD/MM/YYYY')  AND A.CODPROD NOT IN (SELECT CODPROD FROM PCPRODUT WHERE CODSEC IN (587)) AND "+
  "P.CODPROFISSIONAL = D.CODPROFISSIONAL AND D.NUMTRANSVENDA = A.NUMTRANSVENDA AND A.CODOPER = 'S' AND A.CODFILIAL = D.CODFILIAL AND D.DTCANCEL IS NULL  AND D.CODPROFISSIONAL IS NOT NULL AND "+
 "(UPPER(P.PROFISSAO) LIKE 'DECORADOR%' OR UPPER(P.PROFISSAO) LIKE 'ARQUITET%' OR UPPER(P.PROFISSAO) LIKE 'DESIG%') AND P.SITUACAO = 'A' " +
 "GROUP BY P.codprofissional, p.descricao, p.senha, p.percomprof, p.tiposorteio, p.tipoprof, p.dtcadastro, p.codfunccad, p.profissao, p.cnpj, p.rg_ie, p.endereco, p.bairro, p.cep, p.fone, p.email, p.cidade, p.uf, p.celular, p.dtnasc  order by codbrinde DESC"+
                  " FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
    List<pcprofissional> dashboardcoluna(Long fatordivisao, String dtinico, String dtfim);
      @Query(value = "SELECT " +
       " p.codprofissional,p.codbrinde, " +
       " p.descricao, " +
        "p.senha, " +
        "p.percomprof, " +
        "p.tiposorteio, " +
        "p.tipoprof, " +
        "p.dtcadastro, " +
        "p.codfunccad, " +
        "p.profissao, " +
        "p.cnpj, " +
        "p.rg_ie, " +
        "p.endereco, " +
        "p.bairro, " +
        "p.cep, " +
        "p.fone, " +
        "p.email, " +
        "p.cidade, " +
        "p.uf, " +
        "p.celular, " +
        "p.dtnasc " +
       "FROM pcprofissional p where  email=?1 " +
       "GROUP BY p.codprofissional, p.descricao,p.codbrinde, p.senha, p.percomprof, p.tiposorteio, p.tipoprof, p.dtcadastro, p.codfunccad, p.profissao, " +
       " p.cnpj, p.rg_ie, p.endereco, p.bairro, p.cep, p.fone, p.email, p.cidade, p.uf, p.celular, p.dtnasc ", nativeQuery = true)
    Optional<pcprofissional> troca(String email );
}
