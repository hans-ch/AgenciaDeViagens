package Model;

import interfaces.Reservavel;

import java.io.Serializable;

public abstract class Servico implements Reservavel, Serializable {

    protected String id;
    protected String nome;
    protected double precoBase;
    protected int vagas;

    public Servico(String id, String nome, double precoBase, int vagas) {
        this.id = id;
        this.nome = nome;
        this.precoBase = precoBase;
        this.vagas = vagas;
    }

    public abstract double calcularPreco();

    @Override
    public boolean validarVagas(int quantidade) {
        if(quantidade <= this.vagas) {
            this.vagas -= quantidade;
            return true;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }
}
