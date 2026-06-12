package Controller;

import java.util.List;
import java.util.ArrayList;

import Model.PacoteViagem;

public class PacoteViagemController {

    private List<PacoteViagem> listaPacotes = new ArrayList<>();


    public void emitirPacote(PacoteViagem p){
        listaPacotes.add(p);
    }

    public List<PacoteViagem> listar() {
        return listaPacotes;
    }

    public double aplicarDesconto(PacoteViagem p){
        return p.calcularTotal() * 0.85;
    }

    public double aplicarDesconto(PacoteViagem p, String cupom){
        double valorDesconto = p.calcularTotal();

        switch(cupom){
            case "cupom panfleto": valorDesconto -= 50.00;
            break;

            case "cupom sorteado": valorDesconto -= 150.00;
            break;

            case "cupom premiado": valorDesconto -= 300.00;
            break;
        }

        return valorDesconto;
    }

}
