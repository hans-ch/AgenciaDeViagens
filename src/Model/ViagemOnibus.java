package Model;

public class ViagemOnibus extends Transporte {

    private double taxaRodoviaria;

    public ViagemOnibus(String id, String nome, double precoBase, int vagas, String localOrigem,
                        String localDestino, String companhia, String horarioEmbarque, double taxaRodoviaria) {

        // Chamada ao construtor da classe pai (Transporte -> Servico)
        super(id, nome, precoBase, vagas, localOrigem, localDestino, companhia, horarioEmbarque);
        this.taxaRodoviaria = taxaRodoviaria;
    }

    @Override
    public double calcularPreco() {
        // O preço final do ônibus é o preço base + a taxa de embarque da rodoviária
        return this.getPrecoBase() + this.taxaRodoviaria;
    }

    public double getTaxaRodoviaria() {
        return taxaRodoviaria;
    }

    public void setTaxaRodoviaria(double taxaRodoviaria) {
        this.taxaRodoviaria = taxaRodoviaria;
    }
}