package Controller;

import Model.Hospedagem;
import Model.ViagemVoo;
import utils.LoggerService;

import java.util.ArrayList;
import java.util.List;

public class ViagemVooController {

    private final List<ViagemVoo> listaVoos;

    public ViagemVooController() {
        this.listaVoos = new ArrayList<>();
    }

    public void cadastrar(ViagemVoo voo) {
        if (voo == null) {
            throw new IllegalArgumentException("O voo nao pode ser nulo.");
        }

        // Evita duplicidade de voos checando o ID antes de salvar
        if (buscarPorId(voo.getId()) != null) {
            throw new IllegalStateException("Ja existe um voo cadastrado com este ID.");
        }

        this.listaVoos.add(voo);

        // Registra o sucesso do cadastro no log do sistema
        LoggerService.registrarInfo("Voo cadastrado com sucesso: " + voo.getNome() + " (ID: " + voo.getId() + ")");
    }

    public List<ViagemVoo> listar() {
        return new ArrayList<>(listaVoos);
    }

    public ViagemVoo buscarPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        for (ViagemVoo v : this.listaVoos) {
            if (v.getId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        return null;
    }

}
