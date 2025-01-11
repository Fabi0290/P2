package model;

public class Mercadoria_fragil extends Mercadoria {
    

    public Mercadoria_fragil(String id, String nome, String tipo, double peso, double volume, TagIoT tagIoT) {
        super(id, nome, tipo, peso, volume, tagIoT, null); 
    }
 

    // Sobrescrevendo o método canBeMovedWithOthers para mercadorias frágeis
    @Override
    public boolean canBeMovedWithOthers() {
        return false; // Mercadorias frágeis não devem ser movidas com outras
    }

    // Sobrescrevendo o método toString para incluir IDfragil
    @Override
    public String toString() {
        return String.format("MercadoriaFragil[id=%s, nome=%s, tipo=%s, peso=%.2f, volume=%.2f]",
                getId(), getNome(), getTipo(), getPeso(), getVolume());
    }

    /**
     * Método específico para verificar cuidados especiais
     */
    public boolean requiresSpecialHandling() {
        // Sempre retorna true, pois mercadorias frágeis precisam de cuidados especiais
        return true;
    }
}
