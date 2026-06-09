public class Hotel extends Servico{

    int dias;


    @Override
    public double calcularPreco(){
        return preco *= dias;

    }


}
