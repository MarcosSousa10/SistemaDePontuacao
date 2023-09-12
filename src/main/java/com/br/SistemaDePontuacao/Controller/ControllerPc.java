package com.br.SistemaDePontuacao.Controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
import com.br.SistemaDePontuacao.Model.app.pcprofissionals;
import com.br.SistemaDePontuacao.Model.app.vendasProfissional;
import com.br.SistemaDePontuacao.Model.auth.Produto;
import com.br.SistemaDePontuacao.Model.auth.campanha;
import com.br.SistemaDePontuacao.Model.auth.video;
import com.br.SistemaDePontuacao.Repository.app.RepositoryVendasProfissional;
import com.br.SistemaDePontuacao.Repository.app.Repositorypcnfsaid;
import com.br.SistemaDePontuacao.Repository.app.Repositorypcprofissional;
import com.br.SistemaDePontuacao.Repository.app.Repositorypcprofissionals;
import com.br.SistemaDePontuacao.Repository.auth.RepositoryCampanha;
import com.br.SistemaDePontuacao.Repository.auth.Repositoryoo;
import com.br.SistemaDePontuacao.Repository.auth.videoRepository;

@RestController
@RequestMapping("/pc")
@CrossOrigin("*")
public class ControllerPc {
    @Autowired
    private Repositorypcprofissional profissional;
    @Autowired
    private Repositorypcprofissionals profissionals;
    @Autowired
    private RepositoryVendasProfissional vendas;
    @Autowired
    private Repositorypcnfsaid pontuacao;
    @Autowired
    private Repositoryoo parametros;
    @Autowired
    private videoRepository vRepository;
    @Autowired
    private RepositoryCampanha campanha;

    // @GetMapping("/Campanha")
    // public List<campanha> CampanhaSelect() {
    //     return (List<campanha>) campanha.findAll();
    // }

    @PostMapping("/SalvarCampanha")
    public ControllerCampanha salvarCampanha(@RequestBody ControllerCampanha loginss) {
        campanha entidade = loginss.toModel();
        campanha.save(entidade);
        return ControllerCampanha.fromModel(entidade);
    }
    @PutMapping("/EditarCampanha/{id}")
    public ControllerCampanha editarCampanha(@PathVariable Long id, @RequestBody ControllerCampanha campanhaAtualizada) {
        // Primeiro, verifique se a campanha com o ID fornecido existe no banco de dados
        Optional<campanha> campanhaExistente = campanha.findById(id);
        
        if (campanhaExistente.isPresent()) {
            // Atualize os dados da campanha existente com os dados da campanha atualizada
            campanha entidadeExistente = campanhaExistente.get();
            entidadeExistente.setNome(campanhaAtualizada.getNome());
            entidadeExistente.setPeriodo(campanhaAtualizada.getPeriodo());
            // Atualize os outros atributos conforme necessário
            
            // Salve a campanha atualizada de volta no banco de dados
            campanha.save(entidadeExistente);
            
            return ControllerCampanha.fromModel(entidadeExistente);
        } else {
            // Caso a campanha com o ID fornecido não exista, você pode lidar com isso adequadamente
            throw null;
        }
    }

    @GetMapping("/Campanha")
    public ResponseEntity<campanha> CampanhaSelects() {
        Optional<campanha> campanhaOptional = campanha.findById(1L); // Supondo que o ID é do tipo Long
        
        if (campanhaOptional.isPresent()) {
            return ResponseEntity.ok(campanhaOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
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
            return profissional.informacaofiltrocnpj(fatordivisao, dtinicio, dtfim, cnpj).orElse(new pcprofissional());
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
            pcnfsaid result = pontuacao.pontuacao(fatordivisao, dtinicio, dtfim, cod);

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
            return (Optional<pcprofissional>) profissional.informaçoesProfissional(fatordivisao, cod, cod);
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
    public ResponseEntity<Void> atualizar(@PathVariable Integer id, @RequestBody ControllerPcFormRequests produto) {
        Optional<pcprofissionals> produtoExistente = profissionals.findById(id);
        if (produtoExistente.isEmpty()) {
            // public void => throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
        pcprofissionals entidade = produto.toModel();
        entidade.setCodprofissional(id);
        profissionals.save(entidade);
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

    @PostMapping("/video")
    public videoRequest vedeo(@RequestBody videoRequest video) {
        com.br.SistemaDePontuacao.Model.auth.video entidade = video.toModel();
        vRepository.deleteAll();
        vRepository.save(entidade);
        return videoRequest.fromModel(entidade);
    }

    @GetMapping("/tudoVideo")
    public List<video> tudoVideo() {
        return (List<video>) vRepository.findAll();
    }

    @GetMapping("/dashboard/{cnpj}/{mes}")
    public pcprofissional dashboard(@PathVariable String cnpj, @PathVariable String mes) {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            String dtinicio = produto.getDtinicio();
            String dtfim = produto.getDtfim();
            return profissional.dashboard(fatordivisao, mes, dtinicio, dtfim, cnpj).orElse(new pcprofissional());
        } else {
            return new pcprofissional();
        }
    }

    @GetMapping("/senha/{email}")
    public pcprofissional troca(@PathVariable String email) {
        return profissional.troca(email).orElse(new pcprofissional());
    }

    @GetMapping("/dashboardcoluna")
    public List<pcprofissional> dashboardcoluna() {
        Optional<Produto> fatordivisaos = parametros.select();
        if (fatordivisaos.isPresent()) {
            Produto produto = fatordivisaos.get();
            Long fatordivisao = produto.getFatordivisao();
            String dtinicio = produto.getDtinicio();
            String dtfim = produto.getDtfim();
            return profissional.dashboardcoluna(fatordivisao, dtinicio, dtfim);
        } else {
            return new ArrayList<>();
        }
    }

}
