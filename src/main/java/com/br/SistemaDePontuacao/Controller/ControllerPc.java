package com.br.SistemaDePontuacao.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.SistemaDePontuacao.Model.pcnfsaid;
import com.br.SistemaDePontuacao.Model.pcprofissional;
import com.br.SistemaDePontuacao.Model.vendasProfissional;
import com.br.SistemaDePontuacao.Repository.RepositoryVendasProfissional;
import com.br.SistemaDePontuacao.Repository.Repositorypcnfsaid;
import com.br.SistemaDePontuacao.Repository.Repositorypcprofissional;

@RestController
@RequestMapping("/pc")
@CrossOrigin("*")
public class ControllerPc {
    @Autowired
    private Repositorypcprofissional profissional;
    @Autowired
    private RepositoryVendasProfissional vendas;
    @Autowired
    private Repositorypcnfsaid pontuacao;

    @PostMapping("/usuario")
    public ControllerPcFormRequest salvarr(@RequestBody ControllerPcFormRequest loginss) {
        pcprofissional entidade = loginss.toModel();
        profissional.save(entidade);
        return ControllerPcFormRequest.fromModel(entidade);
    }

    @GetMapping("/tudoNovo")
    public List<pcprofissional> obterProdutosNovos() {
        return (List<pcprofissional>) profissional.findAll();
    }

    @GetMapping("/vendas/{profissional}/{dtinicio}/{dtfim}")
    public List<vendasProfissional> vendas(@PathVariable String profissional, @PathVariable String dtinicio,
            @PathVariable String dtfim) {
        return (List<vendasProfissional>) vendas.select(profissional, dtinicio, dtfim);
    }

    @GetMapping("/pontuacao")
    public Optional<pcnfsaid> pontuacao() {
        return (Optional<pcnfsaid>) pontuacao.pontuacao();
    }

    @GetMapping("/informacao")
    public Optional<pcprofissional> informaçoesprofissional() {
        return (Optional<pcprofissional>) profissional.informaçoesProfissional();
    }

    @GetMapping("/teste")
    public void dataeselect() {
        profissional.fetchData();
        return;
    }

    @PutMapping("/update/{email}/{senha}/{uf}/{dtnasc}/{rg_ie}/{fone}/{profissao}/{bairro}/{celular}/{cep}/{cidade}/{descricao}/{dtcadastro}/{endereco}/{cnpj}")
    public ResponseEntity<Object> statusNovo(@PathVariable String email, @PathVariable String senha,
            @PathVariable String uf, @PathVariable String dtnasc, @PathVariable String rg_ie,
            @PathVariable String fone,@PathVariable String profissao, @PathVariable String bairro, @PathVariable String celular,
            @PathVariable String cep,@PathVariable String cidade,@PathVariable String descricao,@PathVariable String dtcadastro,@PathVariable String endereco,@PathVariable String cnpj) {
        Object a = profissional.status(email, senha, uf, dtnasc, rg_ie, fone,profissao, bairro, celular,cep, cidade, descricao, dtcadastro, endereco, cnpj);
        if (a == "0") {
            return ResponseEntity.ok(a);
        } else {
            String errorMessage = "Produto Não Encontrado";
            return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
        }
    }

}
