package Controller;

import Model.ViagemOnibus;
import utils.LoggerService;

import java.util.ArrayList;
import java.util.List;

public class ViagemOnibusController {

    private final List<ViagemOnibus> listaOnibus;

    public ViagemOnibusController() {
        this.listaOnibus = new ArrayList<>();
    }

    public void cadastrar(ViagemOnibus viagemOnibus) {
        if(viagemOnibus.getId() == null) {
            throw new IllegalArgumentException("O id da viagem não pode ser nula");
        }

        if (buscarPorId(viagemOnibus.getId()) != null) {
            throw new IllegalStateException("Ja existe uma viagem cadastrada com este ID.");
        }

        this.listaOnibus.add(viagemOnibus);
        LoggerService.registrarInfo("Viagem de onibus cadastrada com sucesso: " + viagemOnibus.getNome() + " (ID: " + viagemOnibus.getId() + ")");
    }

    public List<ViagemOnibus> listar() {
        return new ArrayList<>(this.listaOnibus);
    }

    public ViagemOnibus buscarPorId(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        for (ViagemOnibus vo : this.listaOnibus) {
            if (vo.getId().equalsIgnoreCase(id)) {
                return vo;
            }
        }
        return null;
    }

}
