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
        @GetMapping("/tudo")
    public List<pcprofissional> obterProdutosN() {
        return (List<pcprofissional>) profissional.SELECT();
    }  
            @GetMapping("/filtro")
    public List<pcprofissional> obterfiltro(@PathVariable String cnpj) {
        return (List<pcprofissional>) profissional.filtro(cnpj);
    }  
      @GetMapping("/codprof/{cnpj}/{senha}")
    public Optional<pcprofissional> codprof(@PathVariable String cnpj,@PathVariable String senha) {
        return (Optional<pcprofissional>) profissional.codprof(cnpj,senha);
    }

    @GetMapping("/vendas/{profissional}/{dtinicio}/{dtfim}")
    public List<vendasProfissional> vendas(@PathVariable String profissional, @PathVariable String dtinicio,
            @PathVariable String dtfim) {
        return (List<vendasProfissional>) vendas.select(profissional, dtinicio, dtfim);
    }
    @GetMapping("/pontuacao/{cod}")
    public Optional<pcnfsaid> pontuacao(@PathVariable String cod) {
        pcnfsaid result = pontuacao.pontuacao(cod);
        return Optional.ofNullable(result != null ? result : new pcnfsaid());
    }

    @GetMapping("/informacao/{cod}")
    public Optional<pcprofissional> informaçoesprofissional(@PathVariable String cod) {
        return (Optional<pcprofissional>) profissional.informaçoesProfissional(cod);
    }

 @GetMapping("/Salvar/{senha}/{email}/{uf}/{dtnasc}/{rg_ie}/{fone}/{profissao}/{bairro}/{celular}/{cep}/{cidade}/{descricao}/"+
    "{endereco}/{cnpj}")
    public ResponseEntity<Object> Salvar(
        @PathVariable String senha,@PathVariable String email,@PathVariable String uf,
        @PathVariable String dtnasc,@PathVariable String rg_ie,@PathVariable String fone,
        @PathVariable String profissao,@PathVariable String bairro,@PathVariable String celular,@PathVariable String cep,
        @PathVariable String cidade,@PathVariable String descricao, @PathVariable String endereco,
        @PathVariable String cnpj) {
        Object a = profissional.salvar(senha,email,uf,dtnasc,rg_ie,fone,profissao,bairro,celular,cep,cidade,descricao,endereco,cnpj);
        if (a == "0") {
            return ResponseEntity.ok(a);
        } else {
            String errorMessage = "Produto Não Encontrado";
            return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
        }
    }

    @GetMapping("/update/{email}/{senha}/{celular}/{descricao}/{uf}/{rg_ie}/{fone}/{profissao}/"+
    "{bairro}/{cep}/{cidade}/{endereco}/{cnpj}/{codprofissional}")
    public ResponseEntity<Object> Editar(
        @PathVariable String email,@PathVariable String senha,@PathVariable String celular,
        @PathVariable String descricao,@PathVariable String uf,@PathVariable String rg_ie,
        @PathVariable String fone,@PathVariable String profissao,@PathVariable String bairro,
        @PathVariable String cep,@PathVariable String cidade, @PathVariable String endereco,
        @PathVariable String cnpj,@PathVariable String codprofissional) {
        Object a = profissional.status(email,senha,celular,descricao,uf,rg_ie,fone,
        profissao,bairro,cep,cidade,endereco,cnpj,codprofissional);
        if (a == "0") {
            return ResponseEntity.ok(a);
        } else {
            String errorMessage = "Produto Não Encontrado";
            return ResponseEntity.status(HttpStatus.OK).body(errorMessage);
        }
    }

}
