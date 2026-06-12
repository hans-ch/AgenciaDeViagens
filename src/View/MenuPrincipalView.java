package View;

import Controller.*;
import java.util.Scanner;

public class MenuPrincipalView {

    private final Scanner scanner;

    // Declarando as Views
    private final ClienteView clienteView;
    private final PasseioView passeioView;
    private final ViagemCruzeiroView cruzeiroView;
    private final ViagemOnibusView onibusView;
    private final ViagemTremView tremView;

    // Construtor injetando os controllers necessários
    public MenuPrincipalView() {
        this.scanner = new Scanner(System.in);

        // Instanciando Controllers
        ClienteController clienteCtrl = new ClienteController();
        PasseioController passeioCtrl = new PasseioController();
        ViagemCruzeiroController cruzeiroCtrl = new ViagemCruzeiroController();
        ViagemOnibusController onibusCtrl = new ViagemOnibusController();
        ViagemTremController tremCtrl = new ViagemTremController();

        // Instanciando Views e passando seus respectivos controllers
        this.clienteView = new ClienteView(clienteCtrl);
        this.passeioView = new PasseioView(passeioCtrl);
        this.cruzeiroView = new ViagemCruzeiroView(cruzeiroCtrl);
        this.onibusView = new ViagemOnibusView(onibusCtrl);
        this.tremView = new ViagemTremView(tremCtrl);
    }

    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== AGÊNCIA DE VIAGENS - CONTROL DESK =====");
            System.out.println("1. Gestão de Clientes");
            System.out.println("2. Gestão de Passeios");
            System.out.println("3. Gestão de Cruzeiros");
            System.out.println("4. Gestão de Ônibus");
            System.out.println("5. Gestão de Trens");
            System.out.println("0. Sair do Sistema");
            System.out.print("Escolha um módulo: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    clienteView.exibirSubMenu();
                    break;
                case 2:
                    passeioView.exibirSubMenu();
                    break;
                case 3:
                    cruzeiroView.exibirSubMenu();
                    break;
                case 4:
                    onibusView.exibirSubMenu();
                    break;
                case 5:
                    tremView.exibirSubMenu();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}