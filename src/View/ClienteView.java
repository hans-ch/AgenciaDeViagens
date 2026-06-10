package View;
public class ClienteView {

    public void printaInfo(String model_string){  

        System.out.println("Informações do Cliente \n-----------------------------\n");
        System.out.println(model_string);  //esse parametro é alimentado pelo toString() do model
        System.out.println("-----------------------------\n");
    }

}
