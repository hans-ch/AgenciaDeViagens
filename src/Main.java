public class Main {
    public static void main(String[] args) throws Exception {

        Cliente pessoa = new Cliente("1234", "Gimini Billy Bob", "93784683300");
        
        ClienteView view = new ClienteView();
        ClienteController controller = new ClienteController(pessoa, view);

        System.out.println(pessoa.toString() + "Usando toString direto do modeL\n");
        controller.showInfo();


        controller.setClienteAttributes("5678", "Chosuke Ponpokopi no Ponpokonna", "54894366452");  //ao invés de criar outro objeto, vc muda usando o set
        // precisa validação de dados?
        controller.showInfo();



//=============================================================================================
    // elementos abaixo são placeholders para Serviços, podem ignorar. Depois mudo de acordo
        Voo serv = new Voo();  
        Hotel hotel = new Hotel();

        serv.preco = 100.00;
        serv.classe = "executivo";

        hotel.preco = 100.00;
        hotel.dias = 366;
//=============================================================================================

        PacoteViagem pacote = new PacoteViagem("67", pessoa);

        pacote.adicionarItem(serv);
        pacote.adicionarItem(hotel);

        System.out.println(pacote.toString());
        System.out.println("Valor total: R$" + pacote.calcularTotal());
    }
}
