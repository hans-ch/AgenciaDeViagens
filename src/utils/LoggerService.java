package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerService {

    private static final String NOME_ARQUIVO = "log.txt";

    private static final DateTimeFormatter DATA_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void registrarInfo(String mensagem){
        gravarLog("INFO", mensagem);
    }

    public static void registrarErro(String mensagem){
        gravarLog("ERRO", mensagem);
    }

    private static void gravarLog(String tipo, String mensagem){
        String hora = LocalDateTime.now().format(DATA_FORMAT);

        String linha = hora + " " + tipo + " " + mensagem;

        try(FileWriter fw = new FileWriter(NOME_ARQUIVO, true); PrintWriter pw = new PrintWriter(fw)) {
            pw.println(linha);
        } catch (IOException e) {
            System.err.println("Falha crítica ao gravar no arquivo de log: " + e.getMessage());
        }
    }
}
