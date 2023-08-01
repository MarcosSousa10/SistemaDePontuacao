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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.SistemaDePontuacao.Model.app.pcnfsaid;
import com.br.SistemaDePontuacao.Model.app.pcprofissional;
import com.br.SistemaDePontuacao.Model.app.vendasProfissional;
import com.br.SistemaDePontuacao.Model.auth.Produto;
import com.br.SistemaDePontuacao.Repository.app.RepositoryVendasProfissional;
import com.br.SistemaDePontuacao.Repository.app.Repositorypcnfsaid;
import com.br.SistemaDePontuacao.Repository.app.Repositorypcprofissional;
import com.br.SistemaDePontuacao.Repository.auth.Repositoryoo;

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
    @Autowired
    private Repositoryoo parametros;

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

    @GetMapping("/tudo")
    public List<pcprofissional> obterFatordivisao() {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            String dtinicio = produto.getDtinicio();
            String dtfim = produto.getDtfim();
            return (List<pcprofissional>) profissional.SELECT(fatordivisao, dtinicio, dtfim);
        } else {
            return null;
        }
    }

    @GetMapping("/filtro")
    public List<pcprofissional> obterfiltro(@PathVariable String cnpj) {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            return (List<pcprofissional>) profissional.filtro(fatordivisao, cnpj);
        } else {

            return null;
        }
    }

    @GetMapping("/codprof/{cnpj}")
    public pcprofissional codprof(@PathVariable String cnpj) {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            return profissional.codprof(fatordivisao, cnpj).orElse(new pcprofissional());
        } else {
            return new pcprofissional();
        }
    }

    @GetMapping("/informacaofitrocnpj/{cnpj}")
    public pcprofissional informacaofitrocnpj(@PathVariable String cnpj) {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            String dtinicio = produto.getDtinicio();
            String dtfim = produto.getDtfim();
            return profissional.informacaofiltrocnpj(fatordivisao, cnpj,dtinicio,dtfim).orElse(new pcprofissional());
        } else {
            return new pcprofissional();
        }
    }

    @GetMapping("/vendas/{profissional}/{dtinicio}/{dtfim}")
    public List<vendasProfissional> vendas(@PathVariable String profissional, @PathVariable String dtinicio,
            @PathVariable String dtfim) {
        return (List<vendasProfissional>) vendas.select(profissional, dtinicio, dtfim);
    }

    @GetMapping("/pontuacao/{cod}")
    public Optional<pcnfsaid> pontuacao(@PathVariable String cod) {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            String dtinicio = produto.getDtinicio();
            String dtfim = produto.getDtfim();
            System.out.println(dtfim + dtinicio + fatordivisao);
            pcnfsaid result = pontuacao.pontuacao(fatordivisao, dtinicio, dtfim,cod);

            return Optional.ofNullable(result != null ? result : new pcnfsaid());
        } else {
            return null;
        }
    }

    @GetMapping("/informacao/{cod}")
    public Optional<pcprofissional> informaçoesprofissional(@PathVariable String cod) {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            return (Optional<pcprofissional>) profissional.informaçoesProfissional(fatordivisao, cod,cod);
        } else {
            return null;
        }
    }

    @GetMapping("/Salvar/{senha}/{email}/{uf}/{dtnasc}/{rg_ie}/{fone}/{profissao}/{bairro}/{celular}/{cep}/{cidade}/{descricao}/"
            +
            "{endereco}/{cnpj}")
    public ResponseEntity<Object> Salvar(
            @PathVariable String senha, @PathVariable String email, @PathVariable String uf,
            @PathVariable String dtnasc, @PathVariable String rg_ie, @PathVariable String fone,
            @PathVariable String profissao, @PathVariable String bairro, @PathVariable String celular,
            @PathVariable String cep,
            @PathVariable String cidade, @PathVariable String descricao, @PathVariable String endereco,
            @PathVariable String cnpj) {
        Object a = profissional.salvar(senha, email, uf, dtnasc, rg_ie, fone, profissao, bairro, celular, cep, cidade,
                descricao, endereco, cnpj);
        if (a == "0") {
            return ResponseEntity.ok(a);
        } else {
            String errorMessage = "Produto Não Encontrado";
            return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
        }
    }
    @PutMapping("/teste/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody ControllerPcFormRequest produto){
        Optional<pcprofissional> produtoExistente=profissional.findById(id);
        if(produtoExistente.isEmpty()){
       //public void =>     throw new ResponseStatusException(HttpStatus.NOT_FOUND);
         return ResponseEntity.notFound().build();
        }
        pcprofissional entidade=produto.toModel();
        entidade.setCodprofissional(id);
        profissional.save(entidade);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/update/{email}/{senha}/{celular}/{descricao}/{uf}/{rg_ie}/{fone}/{profissao}/" +
            "{bairro}/{cep}/{cidade}/{endereco}/{cnpj}/{codprofissional}")
    public ResponseEntity<Object> Editar(
            @PathVariable String email, @PathVariable String senha, @PathVariable String celular,
            @PathVariable String descricao, @PathVariable String uf, @PathVariable String rg_ie,
            @PathVariable String fone, @PathVariable String profissao, @PathVariable String bairro,
            @PathVariable String cep, @PathVariable String cidade, @PathVariable String endereco,
            @PathVariable String cnpj, @PathVariable String codprofissional) {
        Object a = profissional.status(email, senha, celular, descricao, uf, rg_ie, fone,
                profissao, bairro, cep, cidade, endereco, cnpj, codprofissional);
        if (a == "0") {
            return ResponseEntity.ok(a);
        } else {
            String errorMessage = "Produto Não Encontrado";
            return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
        }
    }

    @GetMapping("/updatefatordivisao/{fatordivisao}")
    public String Editarfatordivisao(
            @PathVariable String fatordivisao) {
        Integer fatordivisaos = parametros.fatordivisao(fatordivisao);
        return fatordivisao;

    }

    @GetMapping("/updatedtinicio/{dtinicio}")
    public String Editardtinicio(
            @PathVariable String dtinicio) {
        Integer fatordivisaos = parametros.inicio(dtinicio);
        return dtinicio;

    }

    @GetMapping("/update/{dtfim}")
    public String Editardtfim(
            @PathVariable String dtfim) {
        Integer fatordivisaos = parametros.fim(dtfim);
        return dtfim;

    }

    @GetMapping("/select")
    public ResponseEntity<?> findCode() {
        Optional<Produto> list = parametros.select();
        return new ResponseEntity<>(list, HttpStatus.valueOf(202));
    }

    @PostMapping("/set")
    public ParametrosRequest salvarr(@RequestBody ParametrosRequest loginss) {
        Produto entidade = loginss.toModel();
        parametros.save(entidade);

        return ParametrosRequest.fromModel(entidade);
    }

}
