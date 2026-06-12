package utils;

import java.io.*;

public class ArquivoUtils {

    // Salva o objeto (pode ser uma Lista inteira) em um arquivo .dat
    public static void serializarObjeto(String nomeArquivo, Object objeto) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(objeto);
            LoggerService.registrarInfo("Objeto salvo com sucesso no arquivo: " + nomeArquivo);
        } catch (IOException e) {
            LoggerService.registrarErro("Erro ao serializar arquivo " + nomeArquivo + ": " + e.getMessage());
            System.err.println("Erro ao salvar dados.");
        }
    }

    // Carrega o objeto do arquivo .dat para a memória
    public static Object desserializarObjeto(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            return null; // Retorna null se for a primeira vez rodando o sistema
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            LoggerService.registrarInfo("Objeto carregado com sucesso do arquivo: " + nomeArquivo);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LoggerService.registrarErro("Erro ao desserializar arquivo " + nomeArquivo + ": " + e.getMessage());
            System.err.println("Erro ao carregar dados.");
            return null;
        }
    }
}