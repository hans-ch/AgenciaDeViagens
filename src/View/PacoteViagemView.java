package View;

import Controller.*;

import Model.*;
import utils.LoggerService;

import java.util.List;
import java.util.Scanner;

public class PacoteViagemView {

        private PacoteViagemController pacoteController;
        private ClienteController clienteController;

        private HospedagemController hospedagemController;
        private PasseioController passeioController;
        private ViagemVooController vooController;
        private ViagemOnibusController onibusController;
        private ViagemTremController  tremController;
        private ViagemCruzeiroController  cruzeiroController;

        Scanner scanner;

        public PacoteViagemView(PacoteViagemController pc, ClienteController cc,
                                HospedagemController hc, PasseioController pasc,
                                ViagemVooController vvc, ViagemTremController vtc,
                                ViagemCruzeiroController vcc, ViagemOnibusController voc){

            pacoteController = pc;
            clienteController = cc;
            onibusController = voc;
            hospedagemController = hc;
            passeioController = pasc;
            vooController = vvc;
            tremController = vtc;
            cruzeiroController = vcc;

            this.scanner = new Scanner(System.in);
        }


        public void exibirSubMenu(){

            int opcao;

            do {
                System.out.println("\n===== PACOTE DE VIAGEM =====");
                System.out.println("1. Montar seu pacote");
                System.out.println("2. Checar valor total do pacote");
                System.out.println("0. Voltar");
                System.out.print("Escolha uma opcao: ");

                try {
                    opcao = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    opcao = -1;
                }

                switch (opcao) {
                    case 1:
                        montadorGuiadoPacote();
                        break;
                    case 2:
                        exibirItinerariosCompletos();
                        break;
                    case 0:
                        System.out.println("Retornando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }


            }while(opcao != 0);

        }

    private void montadorGuiadoPacote() {
        System.out.println("\n--- INICIAR MONTAGEM DE PACOTE DE VIAGEM ---");

        // 1. Vincular Cliente
        System.out.print("Digite o ID do Cliente comprador: ");
        String idCliente = scanner.nextLine();
        Cliente cliente = clienteController.buscarPorId(idCliente);

        if (cliente == null) {
            System.out.println("[CANCELADO] Cliente nao encontrado no sistema. Cadastre-o primeiro.");
            return;
        }

        System.out.println("=> Cliente selecionado: " + cliente.getNome());

        // 2. Instanciar o novo pacote modelo
        System.out.print("Digite um ID único para este novo Pacote (ex: PAC-9001): ");
        String idPacote = scanner.nextLine();
        PacoteViagem novoPacote = new PacoteViagem(idPacote, cliente);

        // 3. Loop de inclusao polimorfica de itens/servicos
        boolean adicionandoItens = true;
        while (adicionandoItens) {
            System.out.println("\n--- ADICIONAR ITEM AO PACOTE ---");
            System.out.println("1. Incluir Viagem de Voo");
            System.out.println("2. Incluir Viagem de Onibus");
            System.out.println("3. Incluir Hospedagem (Hotel)");
            System.out.println("0. Concluir Adicao de Itens");
            System.out.print("Escolha o tipo de servico: ");

            int escolhaTipo;
            try {
                escolhaTipo = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                escolhaTipo = -1;
            }

            switch (escolhaTipo) {
                case 1:
                    System.out.print("Digite o ID do Voo desejado: ");
                    String idVoo = scanner.nextLine();
                    ViagemVoo voo = vooController.buscarPorId(idVoo);
                    if (voo != null) {
                        novoPacote.adicionarItem(voo);
                        System.out.println("[OK] Voo adicionado ao carrinho do pacote.");
                    } else {
                        System.out.println("[ERRO] Voo nao localizado.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o ID do Onibus desejado: ");
                    String idOnibus = scanner.nextLine();
                    ViagemOnibus onibus = onibusController.buscarPorId(idOnibus);
                    if (onibus != null) {
                        novoPacote.adicionarItem(onibus);
                        System.out.println("[OK] Rota de onibus adicionada ao carrinho do pacote.");
                    } else {
                        System.out.println("[ERRO] Rota de onibus nao localizada.");
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID da Hospedagem desejada: ");
                    String idHosp = scanner.nextLine();
                    Hospedagem hosp = hospedagemController.buscarPorId(idHosp);
                    if (hosp != null) {
                        novoPacote.adicionarItem(hosp);
                        System.out.println("[OK] Hospedagem adicionada ao carrinho do pacote.");
                    } else {
                        System.out.println("[ERRO] Estabelecimento nao localizado.");
                    }
                    break;

                case 0:
                    adicionandoItens = false;
                    break;

                default:
                    System.out.println("Opcao de servico invalida!");
            }
        }

        // 4. Validar se o pacote contem algum item antes de finalizar
        if (novoPacote.getItens().isEmpty()) {
            System.out.println("[CANCELADO] Nao é possivel emitir um pacote vazio.");
            return;
        }

        // 5. Enviar ao controlador para aplicar regras de negocio, sobrecarga de descontos e emitir
        try {
            pacoteController.emitirPacote(novoPacote);
            System.out.println("\n=============================================");
            System.out.println("[SUCESSO] PACOTE DE VIAGEM EMITIDO COM EXITO!");
            System.out.printf("Valor Total Bruto: R$ %.2f\n", novoPacote.calcularTotal());
            System.out.println("=============================================");
        } catch (Exception e) {
            System.out.println("\n[ERRO CRITICO] Falha ao processar venda: " + e.getMessage());
            LoggerService.registrarErro("Falha na venda do Pacote ID: " + idPacote + ". Motivo: " + e.getMessage());
        }
    }


    private void exibirItinerariosCompletos() {
        System.out.println("\n======= RELATORIO DE ITINERARIOS EMITIDOS =======");
        List<PacoteViagem> pacotes = pacoteController.listar();

        if (pacotes.isEmpty()) {
            System.out.println("Nenhum pacote foi comercializado ate o momento.");
            return;
        }

        for (PacoteViagem p : pacotes) {
            System.out.println("\n==================================================");
            System.out.printf("CODIGO DO PACOTE: %s\n", p.getIdPacote());
            System.out.printf("CLIENTE COMPRADOR: %s (CPF: %s)\n", p.getCliente().getNome(), p.getCliente().getCPF());
            System.out.println("--- DETALHES DOS ITENS CONTRATADOS ---");

            // Varre de forma polimorfica a lista mae List<Servico>
            for (Servico s : p.getItens()) {
                // invoca o calcularPreco polimorfico dinamicamente dependendo da classe real do objeto
                System.out.printf(" • [%s] ID: %s - %s | Valor Item: R$ %.2f\n",
                        s.getClass().getSimpleName(), s.getId(), s.getNome(), s.calcularPreco());
            }

            System.out.println("--------------------------------------------------");
            System.out.printf("VALOR TOTAL DO ITINERARIO: R$ %.2f\n", p.calcularTotal());
            System.out.println("==================================================");
        }
    }
}
