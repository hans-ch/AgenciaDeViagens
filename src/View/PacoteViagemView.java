package View;

import Controller.*;

import Model.*;

import java.util.List;
import java.util.Scanner;

public class PacoteViagemView {

        private PacoteViagemController pacoteController;
        private ClienteController clienteController;

        private HospedagemController hospedagemController;
        private PasseioController passeioController;
        private ViagemVooController vooController;
        private ViagemTremController  tremController;
        private ViagemCruzeiroController  cruzeiroController;

        Scanner scanner;

        public PacoteViagemView(PacoteViagemController pc, ClienteController cc,
                                HospedagemController hc, PasseioController pasc,
                                ViagemVooController vvc, ViagemTremController vtc,
                                ViagemCruzeiroController vcc){

            pacoteController = pc;
            clienteController = cc;

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
                        String id = String.valueOf((pacoteController.listar()).size() + 1); // cria ID para o pacote sendo criado

                        System.out.println("Informe seu ID: ");  // ID do cliente
                        String pesquisa = scanner.nextLine();

                        Cliente aIncluirCliente = clienteController.buscarPorId(pesquisa);

                        PacoteViagem pacote = new PacoteViagem(id, aIncluirCliente);
                        
                        System.out.println("Selecione os serviços que deseja incluir:\n");
                        exibirItinerarioCOmpleto();

                        String opcaoInclusao = "-1";
                        while(opcaoInclusao != "0"){

                            System.out.println("Informe o ID do serviço que deseja incluir: ");
                            opcaoInclusao =  scanner.nextLine();

                            Servico resultadoBusca; 
                        
                            //resultadoBusca = hospedagemController.buscarPorId(opcaoInclusao);
                            //if(resultadoBusca == null){
                            //    resultadoBusca = passeioController.buscarPorId(opcaoInclusao);}
                            //
                            //if(resultadoBusca == null){
                            //    resultadoBusca = vooController.buscarPorId(opcaoInclusao);}

                            //if(resultadoBusca == null){
                                resultadoBusca = tremController.buscarPorId(opcaoInclusao);//}

                            if(resultadoBusca == null){
                                resultadoBusca = cruzeiroController.buscarPorId(opcaoInclusao);}

                            if(resultadoBusca != null){
                                pacote.adicionarItem(resultadoBusca);}
                            
                            System.out.println("Para voltar digite 0");
                        }

                        pacoteController.emitirPacote(pacote); // pacote criado
                        break;

                    case 2:
                        System.out.println("Informe o ID do seu pacote: ");
                        String opcaoBusca =  scanner.nextLine();

                        for(PacoteViagem pacoteDinheiro : pacoteController.listar()){
                            if(opcaoBusca == pacoteDinheiro.getIdPacote()){

                                System.out.println("Valor total: " + pacoteDinheiro.calcularTotal() + "R$");                  
                            }
                        }

                        

                    case 0:
                        System.out.println("Voltando...");
                        break;
                    default:
                        System.out.println("Opcão invalida.");
                }
            } while (opcao != 0);

        }

        public void exibirItinerarioCOmpleto(){
            
            for(ViagemCruzeiro item : cruzeiroController.listar()){
                System.out.printf("ID: %s \nNome: %s \nPreço: %.2f \nVagas: %s ", item.getId(), item.getNome(), item.calcularPreco(), item.getVagas());
                System.out.printf("Local de origem: %s \nLocal de destino: %s \nCompanhia: %s \nEmbarque: %s\n\n", item.getLocalOrigem(), item.getLocalDestino(), item.getCompanhia(), item.getHorarioEmbarque());
            }

            for(ViagemTrem item : tremController.listar()){
                System.out.printf("ID: %s \nNome: %s \nPreço: %.2f \nVagas: %s ", item.getId(), item.getNome(), item.calcularPreco(), item.getVagas());
                System.out.printf("Local de origem: %s \nLocal de destino: %s \nCompanhia: %s \nEmbarque: %s\n\n", item.getLocalOrigem(), item.getLocalDestino(), item.getCompanhia(), item.getHorarioEmbarque());
            }

            //for(ViagemVoo item : vooController.listar()){
            //    System.out.printf("ID: %s \nNome: %s \nPreço: %.2f \nVagas: %s ", item.getId(), item.getNome(), item.calcularPreco(), item.getVagas());
            //    System.out.printf("Local de origem: %s \nLocal de destino: %s \nCompanhia: %s \nEmbarque: %s\n\n", item.getLocalOrigem(), item.getLocalDestino(), item.getCompanhia(), item.getHorarioEmbarque());
            //}
            //
            //for(HospedagemController item : hospedagemController.listar()){
            //    System.out.printf("ID: %s \nNome: %s \nPreço: %.2f \nVagas: %s ", item.getId(), item.getNome(), item.calcularPreco(), item.getVagas());
            //}
            //
            //for(PasseioController item : passeioController.listar()){
            //    System.out.printf("ID: %s \nNome: %s \nPreço: %.2f \nVagas: %s ", item.getId(), item.getNome(), item.calcularPreco(), item.getVagas());
            //}
    
        }






}
