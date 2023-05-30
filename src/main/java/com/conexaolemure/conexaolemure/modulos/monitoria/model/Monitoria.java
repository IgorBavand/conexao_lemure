package com.conexaolemure.conexaolemure.modulos.monitoria.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "monitorias")
public class Monitoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //TODO: fazer conexao com chave estrangeira para usuario(fk_tutor)
    //TODO: fazer conexao com chave estrangeira para disciplina(fk_disciplina)

    @Column(name = "assunto")
    private String assunto;

    @Column(name = "data_inicio")
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

}
