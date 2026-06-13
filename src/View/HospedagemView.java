package View;

import Controller.HospedagemController;
import Model.Hospedagem;
import utils.LoggerService;

import java.util.List;
import java.util.Scanner;

public class HospedagemView {

    private final HospedagemController hospedagemController;
    private final Scanner scanner;

    public HospedagemView(HospedagemController controller) {
        this.hospedagemController = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirSubMenu() {
        int opcao;
        do {
            System.out.println("\n===== GESTAO DE HOSPEDAGENS =====");
            System.out.println("1. Cadastrar Novo Hotel/Pousada");
            System.out.println("2. Listar Hospedagens Cadastradas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    formCadastrarHospedagem();
                    break;
                case 2:
                    exibirHospedagensAtivas();
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void formCadastrarHospedagem() {
        System.out.println("\n--- CADASTRO DE HOSPEDAGEM ---");

        System.out.print("Digite o ID do Servico (ex: HTL-404): ");
        String id = scanner.nextLine();

        System.out.print("Nome do Hotel/Pousada: ");
        String nome = scanner.nextLine();

        System.out.print("Preco da diaria: R$ ");
        double precoBase = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade de quartos disponiveis: ");
        int vagas = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantidade de diarias da reserva: ");
        int dias = Integer.parseInt(scanner.nextLine());

        try {
            // Instancia o modelo de dados de Hospedagem
            Hospedagem novaHospedagem = new Hospedagem(id, nome, precoBase, vagas, dias);

            // Envia para o controlador processar o cadastro e gerar o log.txt interno de INFO
            hospedagemController.cadastrar(novaHospedagem);
            System.out.println("\n[SUCESSO] Hospedagem cadastrada com exito!");

        } catch (Exception e) {
            System.out.println("\n[ERRO] Nao foi possivel cadastrar a hospedagem: " + e.getMessage());
            // Uso do LoggerService capturando falhas de entrada ou validacao de negocio
            LoggerService.registrarErro("Falha no formulario de hospedagem (ID tentado: " + id + "). Motivo: " + e.getMessage());
        }
    }

    private void exibirHospedagensAtivas() {
        System.out.println("\n--- LISTA DE HOSPEDAGENS DISPONIVEIS ---");
        List<Hospedagem> hospedagens = hospedagemController.listar();

        if (hospedagens.isEmpty()) {
            System.out.println("Nenhum estabelecimento hoteleiro cadastrado no momento.");
            return;
        }

        for (Hospedagem h : hospedagens) {
            System.out.println("------------------------------------------------");
            System.out.printf("ID: %s | Estabelecimento: %s\n", h.getId(), h.getNome());
            System.out.printf("Quartos Livres: %d | Periodo Contratado: %d diarias\n", h.getVagas(), h.getDias());
            // Invoca o calcularPreco polimorfico da hospedagem (precoBase * diarias)
            System.out.printf("Preco Total do Periodo: R$ %.2f\n", h.calcularPreco());
        }
        System.out.println("------------------------------------------------");
    }
}
