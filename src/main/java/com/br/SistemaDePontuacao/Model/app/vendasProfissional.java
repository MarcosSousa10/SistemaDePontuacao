package com.br.SistemaDePontuacao.Model.app;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pcclient")
public class vendasProfissional {
    @Id
    private Long numnota;
    private Long vltotal;
    private Long codcli;
    private String cliente;
    private String dtsaida;


}
