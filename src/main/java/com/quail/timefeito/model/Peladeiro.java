package com.quail.timefeito.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Peladeiro {

    @Id
    private String id;

    private String Nome;
    private Integer nivel;
    private String posicao;

    public Peladeiro(String id, String nome, Integer nivel, String posicao) {
        this.id = id;
        Nome = nome;
        this.nivel = nivel;
        this.posicao = posicao;
    }

    public Peladeiro() {
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return Nome;
    }

    public Integer getNivel() {
        return nivel;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peladeiro peladeiro = (Peladeiro) o;
        return Objects.equals(id, peladeiro.id) &&
                Objects.equals(Nome, peladeiro.Nome) &&
                Objects.equals(nivel, peladeiro.nivel) &&
                Objects.equals(posicao, peladeiro.posicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Nome, nivel, posicao);
    }

    @Override
    public String toString() {
        return "Peladeiro{" +
                "id='" + id + '\'' +
                ", Nome='" + Nome + '\'' +
                ", nivel=" + nivel +
                ", posicao='" + posicao + '\'' +
                '}';
    }
}
