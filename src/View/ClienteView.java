package View;

import Controller.ClienteController;
import Model.Cliente;

import java.util.List;
import java.util.Scanner;

public class ClienteView {

    private ClienteController controller;
    Scanner scanner;

    public ClienteView(ClienteController cc){
        controller = cc;
        this.scanner = new Scanner(System.in);
    }

    
    public void exibirSubMenu(){

        int opcao;

        do {
            System.out.println("\n===== CLIENTES =====");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Atualizar");
            System.out.println("5. Deletar");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    formCadastrar();
                    break;

                case 2:
                    exibirClientes();
                    break;

                case 3:
                    System.out.println("Informe o ID a pesquisar: ");
                    String pesquisa = scanner.nextLine();
                    Cliente cliente = controller.buscarPorId(pesquisa);

                    if(cliente != null){
                        System.out.println("CLIENTE -------------");
                        System.out.printf(cliente.toString());
                        System.out.println("---------------------\n");
                    } else{
                        System.out.println("ID não encontrado");
                    }
                    break;
                
                case 4:
                    System.out.println("Informe o ID do cliente a atualizar: ");
                    String pesquisaAtualizar = scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    Cliente clienteAtualizar = new Cliente(pesquisaAtualizar, nome, cpf);
                    boolean sucesso = controller.atualizar(pesquisaAtualizar, clienteAtualizar);
                    
                    if(sucesso){
                        System.out.println("Atualizado com sucesso");
                    } else{
                        System.out.println("Erro ao atualizar");
                    }
                    break;

                case 5:
                    System.out.println("Informe o ID do cliente a excluir: ");
                    String pesquisaDeletar = scanner.nextLine();

                    boolean sucessoDeletar = controller.deletar(pesquisaDeletar);
                    if(sucessoDeletar){
                        System.out.println("Excluido com sucesso");
                    } else{
                        System.out.println("Erro ao excluir");
                    }
                    break;

                case 6:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcão invalida.");
            }
        } while (opcao != 0);

    }

    
    
    public void formCadastrar(){

        System.out.println("CADASTRO ---------------");
        String id = String.valueOf((controller.listar()).size() + 1);  //tamanho da lista de clientes + 1

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

       
        Cliente novoCliente = new Cliente(id, nome, cpf);


        controller.cadastrar(novoCliente);
        System.out.println("Cliente cadastrado");
    } 

    public void exibirClientes(){

        List<Cliente> clientes = controller.listar();

        for(Cliente cliente : clientes){

            System.out.println("CLIENTE -------------");
            System.out.printf("ID: %s \nNome: %s \nCPF: %s\n", cliente.getID(), cliente.getNome(), cliente.getCPF());
            System.out.println("---------------------\n");
        }

    } 



}
