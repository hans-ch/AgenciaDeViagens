package View;

import Controller.*;
import java.util.Scanner;

public class MenuPrincipalView {

    private final Scanner scanner;

    // Declarando as Views
    private final ClienteView clienteView;
    private final HospedagemView hospedagemView;
    private final PacoteViagemView pacoteViagemView;
    private final PasseioView passeioView;
    private final ViagemCruzeiroView cruzeiroView;
    private final ViagemOnibusView onibusView;
    private final ViagemTremView tremView;
    private final ViagemVooView vooView;

    // Construtor injetando os controllers necessários
    public MenuPrincipalView() {
        this.scanner = new Scanner(System.in);

        // Instanciando Controllers
        ClienteController clienteCtrl = new ClienteController();
        HospedagemController hospCtrl = new HospedagemController();
        PacoteViagemController pacoCtrl = new PacoteViagemController();
        PasseioController passeioCtrl = new PasseioController();
        ViagemCruzeiroController cruzeiroCtrl = new ViagemCruzeiroController();
        ViagemOnibusController onibusCtrl = new ViagemOnibusController();
        ViagemTremController tremCtrl = new ViagemTremController();
        ViagemVooController vooCtrl = new ViagemVooController();

        // Instanciando Views e passando seus respectivos controllers
        this.clienteView = new ClienteView(clienteCtrl);
        this.hospedagemView = new HospedagemView(hospCtrl);
        this.pacoteViagemView = new PacoteViagemView(pacoCtrl, clienteCtrl, hospCtrl, passeioCtrl, vooCtrl, tremCtrl, cruzeiroCtrl, onibusCtrl);
        this.passeioView = new PasseioView(passeioCtrl);
        this.cruzeiroView = new ViagemCruzeiroView(cruzeiroCtrl);
        this.onibusView = new ViagemOnibusView(onibusCtrl);
        this.tremView = new ViagemTremView(tremCtrl);
        this.vooView = new ViagemVooView(vooCtrl);
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
            System.out.println("6. Gestão de Voos");
            System.out.println("7. Gestão de Hospedagens");
            System.out.println("8. Gestão de Pacote de Viagem");
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
                case 6:
                    vooView.exibirSubMenu();
                    break;
                case 7:
                    hospedagemView.exibirSubMenu();
                    break;
                case 8:
                    pacoteViagemView.exibirSubMenu();
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