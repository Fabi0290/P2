// Classe Transporte modificada
package model;

import java.util.ArrayList;
import java.util.List;

public class Transporte {
    private String id;
    private double capacidadePeso;
    private double capacidadeVolume;
    private boolean isEspecial;
    private List<Mercadoria> mercadorias;

    public Transporte(String id, double capacidadePeso, double capacidadeVolume, boolean isEspecial) {
        this.id = id;
        this.capacidadePeso = capacidadePeso;
        this.capacidadeVolume = capacidadeVolume;
        this.isEspecial = isEspecial;
        this.mercadorias = new ArrayList<>();
    }

    public boolean carregarMercadoria(Mercadoria mercadoria) {
        if (mercadoria.getPeso() <= capacidadePeso && mercadoria.getVolume() <= capacidadeVolume) {
            if (!mercadoria.getTipo().equalsIgnoreCase("frágil") || isEspecial) {
                mercadorias.add(mercadoria);
                capacidadePeso -= mercadoria.getPeso();
                capacidadeVolume -= mercadoria.getVolume();
                mercadoria.getTagIoT().setLocalizacaoAtual("Em transporte: " + id);
                return true;
            }
        }
        return false;
    }

    public boolean descarregarMercadoria(String mercadoriaId) {
        for (Mercadoria m : mercadorias) {
            if (m.getId().equals(mercadoriaId)) {
                mercadorias.remove(m);
                capacidadePeso += m.getPeso();
                capacidadeVolume += m.getVolume();
                m.getTagIoT().setLocalizacaoAtual("Descarregado de transporte: " + id);
                return true;
            }
        }
        return false;
    }

    public List<Mercadoria> listarMercadorias() {
        return mercadorias;
    }

    @Override
    public String toString() {
        return String.format("Transporte[id=%s, capacidadePeso=%.2f, capacidadeVolume=%.2f, isEspecial=%b, mercadorias=%d]", 
                             id, capacidadePeso, capacidadeVolume, isEspecial, mercadorias.size());
    }
}
