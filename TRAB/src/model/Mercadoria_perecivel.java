package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Mercadoria_perecivel extends Mercadoria {


    public Mercadoria_perecivel(String id, String nome , String tipo, double peso, double volume, TagIoT tagIoT, LocalDate validade) {
        super(id, nome, tipo, peso, volume, tagIoT, validade); 
    }

    // Sobrescrevendo o método canBeMovedWithOthers para mercadorias frágeis
    @Override
    public boolean canBeMovedWithOthers() {
        return false; // Mercadorias frágeis não devem ser movidas com outras
    }

    // Sobrescrevendo o método toString para incluir IDfragil
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String validadeFormatada = getValidade().format(formatter);
        return String.format("MercadoriaPerecivel[id=%s, nome=%s, tipo=%s, peso=%.2f, volume=%.2f, validade=%s]",
                getId(), getNome(), getTipo(), getPeso(), getVolume(), validadeFormatada);
    }

    /**
     * Método específico para verificar cuidados especiais
     */
    public boolean requiresSpecialHandling() {
        // Sempre retorna true, pois mercadorias frágeis precisam de cuidados especiais
        return true;
    }
}
