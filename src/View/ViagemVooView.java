package View;

import Controller.ViagemVooController;
import Model.ViagemVoo;
import utils.LoggerService;

import java.util.List;
import java.util.Scanner;

public class ViagemVooView {

    private final ViagemVooController vooController;
    private final Scanner scanner;

    public ViagemVooView(ViagemVooController vooController) {
        this.vooController = vooController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirSubMenu() {
        int opcao;
        do {
            System.out.println("\n===== GESTAO DE VOOS =====");
            System.out.println("1. Cadastrar Novo Voo");
            System.out.println("2. Listar Voos Disponiveis");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    formCadastrarVoo();
                    break;
                case 2:
                    exibirVoosAtivos();
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void formCadastrarVoo() {
        System.out.println("\n--- CADASTRO DE PASSAGEM AEREA ---");

        System.out.print("Digite o ID do Servico (ex: VOO-777): ");
        String id = scanner.nextLine();

        System.out.print("Nome descritivo do voo (ex: Rio x NY Executivo): ");
        String nome = scanner.nextLine();

        System.out.print("Preco base da passagem: R$ ");
        double precoBase = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantidade de assentos livres na aeronave: ");
        int vagas = Integer.parseInt(scanner.nextLine());

        System.out.print("Aeroporto/Cidade de Origem: ");
        String localOrigem = scanner.nextLine();

        System.out.print("Aeroporto/Cidade de Destino: ");
        String localDestino = scanner.nextLine();

        System.out.print("Nome da Companhia Aerea: ");
        String companhia = scanner.nextLine();

        System.out.print("Horario de Embarque (ex: 08:45): ");
        String horarioEmbarque = scanner.nextLine();

        System.out.print("Taxa de Embarque ANAC / Aeroporto: R$ ");
        double taxaEmbarque = Double.parseDouble(scanner.nextLine());

        try {
            // Instancia o modelo concreto de Voo (passando parametros da heranca + especificos)
            ViagemVoo novoVoo = new ViagemVoo(id, nome, precoBase, vagas,
                    localOrigem, localDestino, companhia, horarioEmbarque, taxaEmbarque);

            // Envia ao controlador para validacoes e salvamento
            vooController.cadastrar(novoVoo);
            System.out.println("\n[SUCESSO] Voo registrado com exito no sistema!");

        } catch (Exception e) {
            System.out.println("\n[ERRO] Nao foi possivel registrar o voo: " + e.getMessage());
            // Registra a falha no Logger de Erros
            LoggerService.registrarErro("Falha no formulario de voo (ID: " + id + "). Motivo: " + e.getMessage());
        }
    }

    private void exibirVoosAtivos() {
        System.out.println("\n--- LISTA DE VOOS DISPONIVEIS ---");
        List<ViagemVoo> voos = vooController.listar();

        if (voos.isEmpty()) {
            System.out.println("Nenhum voo cadastrado no sistema ate o momento.");
            return;
        }

        for (ViagemVoo v : voos) {
            System.out.println("------------------------------------------------");
            System.out.printf("ID: %s | Operacao: %s\n", v.getId(), v.getNome());
            System.out.printf("De: %s  -->  Para: %s (%s)\n", v.getLocalOrigem(), v.getLocalDestino(), v.getCompanhia());
            System.out.printf("Horario: %s | Poltronas Disponiveis: %d\n", v.getHorarioEmbarque(), v.getVagas());
            // O calcularPreco invoca o override polimorfico do modelo (precoBase + taxaEmbarque)
            System.out.printf("Valor Total com Taxas: R$ %.2f\n", v.calcularPreco());
        }
        System.out.println("------------------------------------------------");
    }

}
