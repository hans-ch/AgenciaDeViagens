package Controller;
import java.util.List;
import java.util.ArrayList;

import Model.Cliente;

public class ClienteController {

    private List<Cliente> listaClientes = new ArrayList<>();

    public void cadastrar(Cliente c) {
        listaClientes.add(c);
    }

    public List<Cliente> listar(){
        return listaClientes;  // ?????
    }

    public Cliente buscarPorId(String id){

        for(Cliente cliente : listaClientes){
            if(cliente.getID() == id){
                
                return cliente;
            }
        }
        System.out.println("ID não encontrado");

        return null;
    }

    public boolean atualizar(String id, Cliente novo){
        for(Cliente cliente : listaClientes){
            if(cliente.getID() == id){
                
                int indice = listaClientes.indexOf(cliente);
                listaClientes.set(indice, novo);
                System.out.println("Atualizado com sucesso");
                return true;
            }
        }

        System.out.println("ID não encontrado");
        return false;
    }


    public boolean deletar(String id){
        for(Cliente cliente : listaClientes){
            if(cliente.getID() == id){
                
                listaClientes.remove(cliente);
                System.out.println("Deletado com sucesso");
                return true;
            }
        }

        System.out.println("ID não encontrado");
        return false;
    }

}
