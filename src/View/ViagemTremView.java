package View;

import Controller.ViagemTremController;
import Model.ViagemTrem;

import java.util.List;
import java.util.Scanner;

public class ViagemTremView {

    private final ViagemTremController controller;
    private final Scanner scanner;

    public ViagemTremView(ViagemTremController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirSubMenu() {
        int opcao;
        do {
            System.out.println("\n===== GESTAO DE VIAGENS DE TREM =====");
            System.out.println("1. Cadastrar Nova Rota de Trem");
            System.out.println("2. Listar Rotas de Trem Ativas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    formCadastrarTrem();
                    break;
                case 2:
                    exibirTrensAtivos();
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void formCadastrarTrem() {
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

        System.out.print("Taxa do trilho: ");
        double taxaPedagioTrilho = Double.parseDouble(scanner.nextLine());

        try {
            ViagemTrem novoTrem = new ViagemTrem(id, nome, precoBase, vagas,
                    localOrigem, localDestino, companhia, horarioEmbarque, taxaPedagioTrilho);

            controller.cadastrar(novoTrem);
            System.out.println("Trem cadastrado.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void exibirTrensAtivos() {
        List<ViagemTrem> viagens = controller.listar();

        if (viagens.isEmpty()) {
            System.out.println("Nenhum trem cadastrado.");
            return;
        }

        for (ViagemTrem viagemTrem : viagens) {
            System.out.println("ID: " + viagemTrem.getId());
            System.out.println("Nome: " + viagemTrem.getNome());
            System.out.println("Origem: " + viagemTrem.getLocalOrigem());
            System.out.println("Destino: " + viagemTrem.getLocalDestino());
            System.out.println("Companhia: " + viagemTrem.getCompanhia());
            System.out.println("Horario: " + viagemTrem.getHorarioEmbarque());
            System.out.println("Vagas: " + viagemTrem.getVagas());
            System.out.println("Preco: " + viagemTrem.calcularPreco());
            System.out.println();
        }
    }
}
