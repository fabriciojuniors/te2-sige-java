/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sige.models;

import com.sige.enums.ClassificacaoIndicativa;
import com.sige.enums.Status;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author fabri
 */
@Entity
@Table(name = "eventos")
public class Evento implements Serializable {

    /*
        EXEMPLO DE JSON PARA POST DE EVENTO
        {
	"classificacao": "LIVRE",
	"data_hora": "2022-05-22T12:45:24",
	"descricao": "Teste",
	"id": 0,
	"imagem": "sem foto",
	"local": {
		"id": 13
	},
	"nome": "TESTE",
	"parceiro": {
		"id": 2
	},
	"status": "APROVADO"
        }
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @JsonbProperty(value = "data_hora")
    private LocalDateTime dataHora;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "local_id")
    private Local local;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id", name = "parceiro_id")
    private Parceiro parceiro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonbProperty(value = "classificacao")
    private ClassificacaoIndicativa classificacaoIndicativa;

    @Column
    private String imagem;

    public Evento() {
    }

    public Evento(long id, String nome, String descricao, LocalDateTime dataHora, Local local, Parceiro parceiro, Status status, ClassificacaoIndicativa classificacaoIndicativa, String imagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataHora = dataHora;
        this.local = local;
        this.parceiro = parceiro;
        this.status = status;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.imagem = imagem;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Parceiro getParceiro() {
        return parceiro;
    }

    public void setParceiro(Parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ClassificacaoIndicativa getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evento other = (Evento) obj;
        return this.id == other.id;
    }

}
