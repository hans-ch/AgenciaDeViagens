package Model;

public class ViagemTrem extends Transporte {

    private double taxaPedagioTrilho;

    public ViagemTrem(String id, String nome, double precoBase, int vagas, String localOrigem,
                      String localDestino, String companhia, String horarioEmbarque, double taxaPedagioTrilho) {
        super(id, nome, precoBase, vagas, localOrigem, localDestino, companhia, horarioEmbarque);
        this.taxaPedagioTrilho = taxaPedagioTrilho;
    }

    @Override
    public double calcularPreco() {
        return this.getPrecoBase() + this.taxaPedagioTrilho;
    }

    public double getTaxaPedagioTrilho() {
        return taxaPedagioTrilho;
    }

    public void setTaxaPedagioTrilho(double taxaPedagioTrilho) {
        this.taxaPedagioTrilho = taxaPedagioTrilho;
    }
}
