package Model;
//Favor criar a classe "SERVICO"

public class Hospedagem extends Servico {
    private int dias;

    public Hospedagem(String id, String nome, double precoBase, int vagas, int dias) {
        super(id, nome, precoBase, vagas);
        this.dias = dias;
    }

    @Override
    public double calcularPreco() {
        return this.getPrecoBase() * this.dias;
    }


    @Override
    public boolean validarVagas(int quantidade) {
        if (quantidade > 0 && this.getVagas() >= quantidade) {
            this.setVagas(this.getVagas() - quantidade);
            return true;
        }
        return false;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
}