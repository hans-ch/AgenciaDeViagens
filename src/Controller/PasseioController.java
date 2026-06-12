package Controller;

import Model.Passeio;
import java.util.ArrayList;
import java.util.List;

public class PasseioController {

    private final List<Passeio> listaPasseios;

    public PasseioController() {
        this.listaPasseios = new ArrayList<>();
    }

    public void cadastrar(Passeio passeio) {
        if (buscarPorId(passeio.getId()) != null) {
            throw new IllegalStateException("Já existe um passeio cadastrado com este ID.");
        }
        this.listaPasseios.add(passeio);
    }

    public List<Passeio> listar() {
        return new ArrayList<>(this.listaPasseios);
    }

    public Passeio buscarPorId(String id) {
        for (Passeio passeio : this.listaPasseios) {
            if (passeio.getId().equalsIgnoreCase(id)) {
                return passeio;
            }
        }
        return null;
    }
}