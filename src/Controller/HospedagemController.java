package Controller;

import Model.Hospedagem;
import utils.LoggerService;

import java.util.ArrayList;
import java.util.List;

public class HospedagemController {

    private List<Hospedagem> listaHospedagens;

    public HospedagemController() {
        listaHospedagens = new ArrayList<>();
    }

    public void cadastrar(Hospedagem hospedagem) {
        if (hospedagem == null) {
            throw new IllegalArgumentException("Hospedagem não pode ser nulo.");
        }
        if (buscarPorId(hospedagem.getId()) != null) {
            throw new IllegalStateException("Ja existe uma hospedagem cadastrada com este ID.");
        }
        this.listaHospedagens.add(hospedagem);
        LoggerService.registrarInfo("Hospedagem cadastrada com sucesso: " + hospedagem.getNome() + " (ID: " + hospedagem.getId() + ")");
    }

    public List<Hospedagem> listar() {
        // Retorna um novo ArrayList para proteger a lista original de alteracoes externas
        return new ArrayList<>(this.listaHospedagens);
    }

    public Hospedagem buscarPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        for (Hospedagem h : this.listaHospedagens) {
            if (h.getId().equalsIgnoreCase(id)) {
                return h;
            }
        }
        return null;
    }
}
