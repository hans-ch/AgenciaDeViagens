package View;

import Controller.PasseioController;
import Model.Passeio;
import java.util.List;
import java.util.Scanner;

public class PasseioView {

    private final PasseioController controller;
    private final Scanner scanner;

    public PasseioView(PasseioController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirSubMenu() {
        int opcao;
        do {
            System.out.println("\n===== GESTÃO DE PASSEIOS =====");
            System.out.println("1. Cadastrar Passeio");
            System.out.println("2. Listar Passeios");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    formCadastrarPasseio();
                    break;
                case 2:
                    exibirPasseiosAtivos();
                    break;
                case 0:
                    System.out.println("Retornando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void formCadastrarPasseio() {
        System.out.print("ID: ");
        String id = scanner.nextLine();

        System.out.print("Nome do Passeio: ");
        String nome = scanner.nextLine();

        System.out.print("Preco Base: ");
        double precoBase = Double.parseDouble(scanner.nextLine());

        System.out.print("Vagas: ");
        int vagas = Integer.parseInt(scanner.nextLine());

        System.out.print("Local do Passeio: ");
        String local = scanner.nextLine();

        System.out.print("Inclui Guia (true/false): ");
        boolean guia = Boolean.parseBoolean(scanner.nextLine());

        Passeio novoPasseio = new Passeio(id, nome, precoBase, vagas, local, guia);
        controller.cadastrar(novoPasseio);
        System.out.println("Passeio cadastrado com sucesso.");
    }

    private void exibirPasseiosAtivos() {
        List<Passeio> passeios = controller.listar();
        if (passeios.isEmpty()) {
            System.out.println("Nenhum passeio cadastrado.");
            return;
        }
        for (Passeio p : passeios) {
            System.out.printf("ID: %s | Nome: %s | Local: %s | Guia: %b | Preço Final: R$ %.2f\n",
                    p.getId(), p.getNome(), p.getLocalPasseio(), p.isIncluiGuia(), p.calcularPreco());
        }
    }
}