package Model;
//Favor criar a classe "SERVICO"


public class Voo extends Servico {
    private double taxaEmbarque;

    public Voo(String id, String nome, double precoBase, int vagas, double taxaEmbarque) {
        super(id, nome, precoBase, vagas);
        this.taxaEmbarque = taxaEmbarque;
    }

    @Override
    public double calcularPreco() {
        return this.getPrecoBase() + this.taxaEmbarque;
    }

    @Override
    public boolean validarVagas(int quantidade) {
        if (quantidade > 0 && this.getVagas() >= quantidade) {
            // Deduz as vagas do inventário caso seja válido
            this.setVagas(this.getVagas() - quantidade);
            return true;
        }
        return false;
    }

    public double getTaxaEmbarque() {
        return taxaEmbarque;
    }
    public void setTaxaEmbarque(double taxaEmbarque) {
        this.taxaEmbarque = taxaEmbarque;
    }
}

