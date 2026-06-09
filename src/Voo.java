import Model.Servico;

public class Voo extends Servico {

    String classe;


    @Override
    public double calcularPreco(){
        switch(classe){
            case "comercial":
                preco += 300.00;
            case "executivo":
                preco += 600.00;
            case "primera classe":
                preco += 1200.00;
        }

        return preco;
    }
}
