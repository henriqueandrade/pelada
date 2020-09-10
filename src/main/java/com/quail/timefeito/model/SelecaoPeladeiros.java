package com.quail.timefeito.model;

import java.util.List;
import java.util.Objects;

public class SelecaoPeladeiros {

    private Integer timeId;
    private List<Peladeiro> jogadores;

    public SelecaoPeladeiros() {
    }

    public SelecaoPeladeiros(Integer timeId, List<Peladeiro> jogadores) {
        this.timeId = timeId;
        this.jogadores = jogadores;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
    }

    public List<Peladeiro> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Peladeiro> jogadores) {
        this.jogadores = jogadores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelecaoPeladeiros time = (SelecaoPeladeiros) o;
        return Objects.equals(timeId, time.timeId) &&
                Objects.equals(jogadores, time.jogadores);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeId, jogadores);
    }

}
