package Model;

public class Passeio extends Servico {

    private String localPasseio;
    private boolean incluiGuia;

    public Passeio(String id, String nome, double precoBase, int vagas, String localPasseio, boolean incluiGuia) {
        super(id, nome, precoBase, vagas);
        this.localPasseio = localPasseio;
        this.incluiGuia = incluiGuia;
    }

    @Override
    public double calcularPreco() {
        // Exemplo de regra de negócio: Se tiver guia, cobra uma taxa extra
        return incluiGuia ? this.getPrecoBase() + 150.00 : this.getPrecoBase();
    }

    public String getLocalPasseio() {
        return localPasseio;
    }

    public void setLocalPasseio(String localPasseio) {
        this.localPasseio = localPasseio;
    }

    public boolean isIncluiGuia() {
        return incluiGuia;
    }

    public void setIncluiGuia(boolean incluiGuia) {
        this.incluiGuia = incluiGuia;
    }
}