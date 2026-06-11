package View;

import Controller.ViagemOnibusController;
import Model.ViagemOnibus;

import java.util.List;
import java.util.Scanner;

public class ViagemOnibusView {

    private final ViagemOnibusController controller;
    private final Scanner scanner;

    public ViagemOnibusView(ViagemOnibusController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirSubMenu() {
        int opcao;
        do {
            System.out.println("\n===== GESTAO DE VIAGENS DE ONIBUS =====");
            System.out.println("1. Cadastrar Nova Rota de Onibus");
            System.out.println("2. Listar Rotas de Onibus Ativas");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    formCadastrarOnibus();
                    break;
                case 2:
                    exibirOnibusAtivos();
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void formCadastrarOnibus() {
        System.out.println("\n--- CADASTRO DE ROTA DE ONIBUS ---");

        System.out.print("Digite o ID do Servico (ex: BUS-101): ");
        String id = scanner.nextLine();

        System.out.print("Nome descritivo da viagem: ");
        String nome = scanner.nextLine();

        System.out.print("Preco base da passagem: R$ ");
        double precoBase = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade de assentos totais disponiveis: ");
        int vagas = Integer.parseInt(scanner.nextLine());

        System.out.print("Cidade de Origem: ");
        String localOrigem = scanner.nextLine();

        System.out.print("Cidade de Destino: ");
        String localDestino = scanner.nextLine();

        System.out.print("Nome da Viacao/Companhia: ");
        String companhia = scanner.nextLine();

        System.out.print("Horario de Embarque (ex: 14:30): ");
        String horarioEmbarque = scanner.nextLine();

        System.out.print("Taxa de Embarque do Terminal Rodoviario: R$ ");
        double taxaRodoviaria = Double.parseDouble(scanner.nextLine());

        try {
            ViagemOnibus novoOnibus = new ViagemOnibus(id, nome, precoBase, vagas,
                    localOrigem, localDestino, companhia, horarioEmbarque, taxaRodoviaria);

            controller.cadastrar(novoOnibus);
            System.out.println("\n[SUCESSO] Rota de onibus cadastrada com exito!");

        } catch (Exception e) {
            System.out.println("\n[ERRO] Nao foi possivel cadastrar a rota: " + e.getMessage());
        }
    }

    private void exibirOnibusAtivos() {
        System.out.println("\n--- LISTA DE ROTAS DE ONIBUS DISPONIVEIS ---");
        List<ViagemOnibus> viagens = controller.listar();

        if (viagens.isEmpty()) {
            System.out.println("Nenhuma rota de onibus cadastrada no momento.");
            return;
        }

        for (ViagemOnibus vo : viagens) {
            System.out.println("------------------------------------------------");
            System.out.printf("ID: %s | Rota: %s\n", vo.getId(), vo.getNome());
            System.out.printf("De: %s  -->  Para: %s (%s)\n", vo.getLocalOrigem(), vo.getLocalDestino(), vo.getCompanhia());
            System.out.printf("Embarque: %s | Assentos Livres: %d\n", vo.getHorarioEmbarque(), vo.getVagas());
            System.out.printf("Preco Final do Bilhete: R$ %.2f\n", vo.calcularPreco());
        }
        System.out.println("------------------------------------------------");
    }
}
