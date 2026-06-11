package Model;

public class ViagemCruzeiro extends Transporte {

    private double taxaPortuaria;

    public ViagemCruzeiro(String id, String nome, double precoBase, int vagas, String localOrigem,
                          String localDestino, String companhia, String horarioEmbarque, double taxaPortuaria) {
        super(id, nome, precoBase, vagas, localOrigem, localDestino, companhia, horarioEmbarque);
        this.taxaPortuaria = taxaPortuaria;
    }

    @Override
    public double calcularPreco() {
        return this.getPrecoBase() + this.taxaPortuaria;
    }

    public double getTaxaPortuaria() {
        return taxaPortuaria;
    }

    public void setTaxaPortuaria(double taxaPortuaria) {
        this.taxaPortuaria = taxaPortuaria;
    }
}
