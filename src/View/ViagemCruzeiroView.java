package View;

import Controller.ViagemCruzeiroController;
import Model.ViagemCruzeiro;

import java.util.List;
import java.util.Scanner;

public class ViagemCruzeiroView {

    private final ViagemCruzeiroController controller;
    private final Scanner scanner;

    public ViagemCruzeiroView(ViagemCruzeiroController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirSubMenu() {
        int opcao;
        do {
            System.out.println("\n===== GESTAO DE VIAGENS DE CRUZEIRO =====");
            System.out.println("1. Cadastrar Nova Viagem de Cruzeiro");
            System.out.println("2. Listar Viagens de Cruzeiro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    formCadastrarCruzeiro();
                    break;
                case 2:
                    exibirCruzeirosAtivos();
                    break;
                case 0:
                    System.out.println("Voltando...");
                    break;
                default:
                    System.out.println("Opcao invalida.");
            }
        } while (opcao != 0);
    }

    private void formCadastrarCruzeiro() {
        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preco base: ");
        double precoBase = Double.parseDouble(scanner.nextLine());

        System.out.print("Vagas: ");
        int vagas = Integer.parseInt(scanner.nextLine());

        System.out.print("Origem: ");
        String localOrigem = scanner.nextLine();

        System.out.print("Destino: ");
        String localDestino = scanner.nextLine();

        System.out.print("Companhia: ");
        String companhia = scanner.nextLine();

        System.out.print("Horario: ");
        String horarioEmbarque = scanner.nextLine();

        System.out.print("Taxa portuaria: ");
        double taxaPortuaria = Double.parseDouble(scanner.nextLine());

        ViagemCruzeiro novoCruzeiro = new ViagemCruzeiro(id, nome, precoBase, vagas,
                localOrigem, localDestino, companhia, horarioEmbarque, taxaPortuaria);

        controller.cadastrar(novoCruzeiro);
        System.out.println("Cruzeiro cadastrado.");
    }

    private void exibirCruzeirosAtivos() {
        List<ViagemCruzeiro> viagens = controller.listar();

        if (viagens.isEmpty()) {
            System.out.println("Nenhum cruzeiro cadastrado.");
            return;
        }

        for (ViagemCruzeiro viagemCruzeiro : viagens) {
            System.out.println("ID: " + viagemCruzeiro.getId());
            System.out.println("Nome: " + viagemCruzeiro.getNome());
            System.out.println("Origem: " + viagemCruzeiro.getLocalOrigem());
            System.out.println("Destino: " + viagemCruzeiro.getLocalDestino());
            System.out.println("Companhia: " + viagemCruzeiro.getCompanhia());
            System.out.println("Horario: " + viagemCruzeiro.getHorarioEmbarque());
            System.out.println("Vagas: " + viagemCruzeiro.getVagas());
            System.out.println("Preco: " + viagemCruzeiro.calcularPreco());
            System.out.println();
        }
    }
}
