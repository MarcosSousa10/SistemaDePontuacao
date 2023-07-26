package com.br.SistemaDePontuacao.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.SistemaDePontuacao.Model.pcprofissional;
import com.br.SistemaDePontuacao.Model.vendasProfissional;
import com.br.SistemaDePontuacao.Repository.RepositoryVendasProfissional;
import com.br.SistemaDePontuacao.Repository.Repositorypcprofissional;

@RestController
@RequestMapping("/pc")
@CrossOrigin("*")
public class ControllerPc {
    @Autowired
    private Repositorypcprofissional profissional;
    @Autowired
    private RepositoryVendasProfissional vendas;
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
    public List<vendasProfissional> vendas(@PathVariable String profissional, @PathVariable String dtinicio,@PathVariable String dtfim) {
        return (List<vendasProfissional>) vendas.select(profissional,dtinicio,dtfim);
    }
}
