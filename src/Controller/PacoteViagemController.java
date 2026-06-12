package Controller;

import java.util.List;
import java.util.ArrayList;

import Model.PacoteViagem;

public class PacoteViagemController {

    private List<PacoteViagem> listaPacotes = new ArrayList<>();


    public void emitirPacote(PacoteViagem p){
        listaPacotes.add(p);
    }


    public List<PacoteViagem> listar(){
        return listaPacotes;
    }


    public double aplicarDesconto(PacoteViagem p){

        double valorDesconto = (p.calcularTotal()) * 0.85;
        return valorDesconto;
    }

    public double aplicarDesconto(PacoteViagem p, String cupom){
        double valorDesconto = p.calcularTotal();

        switch(cupom){
            case "cupom panfleto": valorDesconto *= 0.90;
            break;

            case "cupom sorteado": valorDesconto *= 0.75;
            break;

            case "cupom premiado": valorDesconto *= 0.50;
            break;
        }

        return valorDesconto;
    }
}
