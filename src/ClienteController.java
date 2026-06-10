import Model.Cliente;
import View.ClienteView;

public class ClienteController {

    private Cliente model;
    private ClienteView view;

    public ClienteController(Cliente m, ClienteView v){
        model = m;
        view = v;
    }

    public void setClienteAttributes(String id, String nome, String cpf) {
        // precisa de um set pra cada será?
        model.setID(id);
        model.setNome(nome);
        model.setCPF(cpf);
    }

    public void showInfo() {

        String info = model.toString();  //põe toString do model em uma variavel
        view.printaInfo(info);  // usa o view para "printar" essa variavel
    }

}
