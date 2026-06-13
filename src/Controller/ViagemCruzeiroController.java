package Controller;

import Model.ViagemCruzeiro;
import utils.LoggerService;

import java.util.ArrayList;
import java.util.List;

public class ViagemCruzeiroController {

    private final List<ViagemCruzeiro> listaCruzeiros;

    public ViagemCruzeiroController() {
        this.listaCruzeiros = new ArrayList<>();
    }

    public void cadastrar(ViagemCruzeiro viagemCruzeiro) {
        if(viagemCruzeiro == null) {
            throw new IllegalArgumentException("A viagem de cruzeiro não pode ser nula");
        }

        if (buscarPorId(viagemCruzeiro.getId()) != null) {
            throw new IllegalStateException("Ja existe uma viagem de cruzeiro cadastrada com este ID.");
        }

        LoggerService.registrarInfo("Viagem de cruzeiro cadastrado com sucesso: " + viagemCruzeiro.getNome() + " (ID: " + viagemCruzeiro.getId() + ")");
        this.listaCruzeiros.add(viagemCruzeiro);
    }

    public List<ViagemCruzeiro> listar() {
        return new ArrayList<>(this.listaCruzeiros);
    }

    public ViagemCruzeiro buscarPorId(String id) {
        for (ViagemCruzeiro viagemCruzeiro : this.listaCruzeiros) {
            if (viagemCruzeiro.getId().equalsIgnoreCase(id)) {
                return viagemCruzeiro;
            }
        }
        return null;
    }
}
