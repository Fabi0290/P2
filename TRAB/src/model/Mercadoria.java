package model;

import java.time.LocalDate;

public class Mercadoria {
    private String id;
    private String nome;
    private String tipo; // normal, frágil, perecível
    private double peso;
    private double volume;
    private TagIoT tagIoT;
    private LocalDate validade; // Opcional, para perecíveis

    public Mercadoria(){}
    public Mercadoria(String id, String nome, String tipo, double peso, double volume, TagIoT tagIoT, LocalDate validade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.peso = peso;
        this.volume = volume;
        this.tagIoT = tagIoT;
        this.validade = validade;
    }
    

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPeso() {
        return peso;
    }

    public double getVolume() {
        return volume;
    }

    public TagIoT getTagIoT() {
        return tagIoT;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public boolean isValid() {
        return !"perecível".equalsIgnoreCase(tipo) || (validade != null && validade.isAfter(LocalDate.now()));
    }

    public boolean needsSpecialTransport() {
        return "frágil".equalsIgnoreCase(tipo) || ("perecível".equalsIgnoreCase(tipo) && !isValid());
    }

    public boolean canBeMovedWithOthers() {
        return !"frágil".equalsIgnoreCase(tipo);
    }

    @Override
    public String toString() {
        return String.format("Mercadoria[id=%s, descricao=%s, tipo=%s, peso=%.2f, volume=%.2f]", id, nome, tipo, peso, volume);
    }

    /**
     * Compara as mercadorias perecíveis por data de validade.
     * Útil para ordenação em listas.
     */
    public int compareByValidity(Mercadoria other) {
        if (this.validade == null || other.validade == null) {
            return 0;
        }
        return this.validade.compareTo(other.validade);
    }

    /**
     * Verifica se a mercadoria precisa ser enviada para reciclagem.
     */
    public boolean needsRecycling() {
        return "perecível".equalsIgnoreCase(tipo) && validade != null && validade.isBefore(LocalDate.now());
    }
}
