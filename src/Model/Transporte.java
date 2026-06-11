package Model;

public class Transporte extends Servico{

    protected String localOrigem;
    protected String localDestino;
    protected String companhia;
    protected String horarioEmbarque;

    public Transporte(String id, String nome, double precoBase, int vagas, String localOrigem, String localDestino, String companhia, String horarioEmbarque) {
        super(id, nome, precoBase, vagas);
        this.localOrigem = localOrigem;
        this.localDestino = localDestino;
        this.companhia = companhia;
        this.horarioEmbarque = horarioEmbarque;
    }

    @Override
    public double calcularPreco() {
        return 0;
    }

    public String getLocalOrigem() {
        return localOrigem;
    }

    public void setLocalOrigem(String localOrigem) {
        this.localOrigem = localOrigem;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public String getCompanhia() {
        return companhia;
    }

    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }

    public String getHorarioEmbarque() {
        return horarioEmbarque;
    }

    public void setHorarioEmbarque(String horarioEmbarque) {
        this.horarioEmbarque = horarioEmbarque;
    }
}
