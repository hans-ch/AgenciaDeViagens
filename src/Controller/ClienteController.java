package Controller;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import Model.Cliente;
import utils.LoggerService;

public class ClienteController {

    private List<Cliente> listaClientes = new ArrayList<>();

    public void cadastrar(Cliente c) {
        listaClientes.add(c);
        LoggerService.registrarInfo("Cliente cadastrado com sucesso: " + c.getNome() + " (ID: " + c.getID() + ")");
    }

    public List<Cliente> listar() {
        return listaClientes;  
    }

    public Cliente buscarPorId(String id){

        for(Cliente cliente : listaClientes){
            if(Objects.equals(cliente.getID(), id)){
                
                return cliente;
            }
        }
        
        return null;
    }

    public boolean atualizar(String id, Cliente novo){
        for(Cliente cliente : listaClientes){
            if(Objects.equals(cliente.getID(), id)){
                
                int indice = listaClientes.indexOf(cliente);
                listaClientes.set(indice, novo);
                return true;
            }
        }
        return false;
    }


    public boolean deletar(String id){
        for(Cliente cliente : listaClientes){
            if(Objects.equals(cliente.getID(), id)){

                LoggerService.registrarInfo("Cliente removido com sucesso: " + cliente.getNome() + " (ID: " + cliente.getID() + ")");
                listaClientes.remove(cliente);
                return true;
            }
        }

        return false;
    }

}
