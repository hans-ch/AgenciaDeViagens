import java.util.List;
import java.util.ArrayList;

public class PacoteViagem {

    private String id_pacote;
    private Cliente obj_cliente;  
    /*private ?*/ List<Servico> itens = new ArrayList<>();

    public PacoteViagem(String id_pacote, Cliente obj_cliente){

        this.id_pacote = id_pacote;
        this.obj_cliente = obj_cliente;
    }

    public void adicionarItem(Servico s){
        itens.add(s);

    }

    public double calcularTotal(){

        double valor_total = 0.0;

        for(Servico item : itens){
            valor_total += item.calcularPreco();}  //depende do nome da função em Servico

        return valor_total; 
    }

    @Override
    public String toString(){

        String info_cliente = obj_cliente.toString();  //vem do toString da classe cliente

        return "ID do pacote: " + id_pacote + "\n" + info_cliente;
    }


}
