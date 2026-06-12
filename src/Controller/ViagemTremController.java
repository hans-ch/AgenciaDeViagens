package Controller;

import Model.ViagemTrem;
import utils.LoggerService;

import java.util.ArrayList;
import java.util.List;

public class ViagemTremController {

    private final List<ViagemTrem> listaTrens;

    public ViagemTremController() {
        this.listaTrens = new ArrayList<>();
    }

    public void cadastrar(ViagemTrem viagemTrem) {
        if (viagemTrem == null) {
            throw new IllegalArgumentException("Viagem de Trem não pode ser nulo");
        }

        if (buscarPorId(viagemTrem.getId()) != null) {
            throw new IllegalStateException("Ja existe uma viagem de trem cadastrada com este ID.");
        }
        this.listaTrens.add(viagemTrem);
        LoggerService.registrarInfo("Viagem de trem cadastrada com sucesso: " + viagemTrem.getNome() + " (ID: " + viagemTrem.getId() + ")");
    }

    public List<ViagemTrem> listar() {
        return new ArrayList<>(this.listaTrens);
    }

    public ViagemTrem buscarPorId(String id) {
        for (ViagemTrem viagemTrem : this.listaTrens) {
            if (viagemTrem.getId().equalsIgnoreCase(id)) {
                return viagemTrem;
            }
        }
        return null;
    }
}
