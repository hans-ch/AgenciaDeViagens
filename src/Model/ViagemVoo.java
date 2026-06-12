package Model;
//Favor criar a classe "SERVICO"


public class ViagemVoo extends Transporte {
    private double taxaEmbarque;

    public ViagemVoo(String id, String nome, double precoBase, int vagas, String localOrigem, String localDestino, String companhia, String horarioEmbarque, double taxaEmbarque) {
        super(id, nome, precoBase, vagas, localOrigem, localDestino, companhia, horarioEmbarque);
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

