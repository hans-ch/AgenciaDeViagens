package Model;

import java.util.List;
import java.util.ArrayList;

public class PacoteViagem {

    private String id_pacote;
    private Cliente cliente;  
    private List<Servico> itens = new ArrayList<>();

    public PacoteViagem(String id_pacote, Cliente cliente){

        this.id_pacote = id_pacote;
        this.cliente = cliente;
    }

    public void adicionarItem(Servico s){
        itens.add(s);

    }

    public double calcularTotal(){

        double valor_total = 0.0;

        for(Servico item : itens){
            valor_total += item.calcularPreco();}  //depende do nome da função em Model.Servico

        return valor_total; 
    }

    public String getIdPacote() {
        return id_pacote;}
    public void setIdPacote(String id_pacote) {
        this.id_pacote = id_pacote;}

    public Cliente getCliente() {
        return cliente;}
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;}

}
