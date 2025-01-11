package model;

import java.util.ArrayList;
import java.util.List;

public class Transporte {
    private String id;
    private double capacidadePeso;
    private double capacidadeVolume;
    private boolean isEspecial;
    private List<Mercadoria> mercadorias;
    
    // Variáveis para controlar o peso e volume atual
    private double pesoAtual = 0;
    private double volumeAtual = 0;

    // Construtor
    public Transporte(String id, double capacidadePeso, double capacidadeVolume, boolean isEspecial) {
        this.id = id;
        this.capacidadePeso = capacidadePeso;
        this.capacidadeVolume = capacidadeVolume;
        this.isEspecial = isEspecial;
        this.mercadorias = new ArrayList<>();
    }

    // Método para transportar mercadorias
    public boolean transportarMercadoria(Mercadoria mercadoria, boolean paraArmazem) {
        if (mercadoria.getTipo().equalsIgnoreCase("frágil")) {
            System.out.println("Erro: Não podemos aceitar mercadorias frágeis.");
            return false; // Não aceita mercadoria frágil
        }

        if (paraArmazem) {
            // Verifica se o armazém tem capacidade suficiente para a mercadoria
            if (pesoAtual + mercadoria.getPeso() <= capacidadePeso && 
                volumeAtual + mercadoria.getVolume() <= capacidadeVolume) {

                // Adiciona a mercadoria no armazém
                mercadorias.add(mercadoria);
                pesoAtual += mercadoria.getPeso();
                volumeAtual += mercadoria.getVolume();
                mercadoria.getTagIoT().setLocalizacaoAtual("Armazém: " + id);
                System.out.println("Mercadoria levada para o armazém com sucesso!");
                return true;
            } else {
                System.out.println("Erro: Não há capacidade suficiente no armazém.");
                return false;
            }
        } else {
            // Verifica se o transporte tem capacidade suficiente para a mercadoria
            if (pesoAtual + mercadoria.getPeso() <= capacidadePeso && 
                volumeAtual + mercadoria.getVolume() <= capacidadeVolume) {

                // Adiciona a mercadoria no transporte
                mercadorias.add(mercadoria);
                pesoAtual += mercadoria.getPeso();
                volumeAtual += mercadoria.getVolume();
                mercadoria.getTagIoT().setLocalizacaoAtual("Em transporte: " + id);
                System.out.println("Mercadoria levada para o transporte com sucesso!");
                return true;
            } else {
                System.out.println("Erro: Não há capacidade suficiente no transporte.");
                return false;
            }
        }
    }

    // Método para descarregar mercadoria
    public boolean descarregarMercadoria(String mercadoriaId) {
        for (Mercadoria m : mercadorias) {
            if (m.getId().equals(mercadoriaId)) {
                mercadorias.remove(m);
                pesoAtual -= m.getPeso();
                volumeAtual -= m.getVolume();
                m.getTagIoT().setLocalizacaoAtual("Descarregado de transporte: " + id);
                System.out.println("Mercadoria descarregada com sucesso!");
                return true;
            }
        }
        System.out.println("Erro: Mercadoria não encontrada.");
        return false;
    }

    // Método para listar todas as mercadorias no transporte
    public List<Mercadoria> listarMercadorias() {
        return mercadorias;
    }

    // Método toString para descrição do transporte
    @Override
    public String toString() {
        return String.format("Transporte[id=%s, capacidadePeso=%.2f, capacidadeVolume=%.2f, isEspecial=%b, mercadorias=%d]", 
                             id, capacidadePeso, capacidadeVolume, isEspecial, mercadorias.size());
    }

    // Métodos getters e setters para controlar o peso e volume atual
    public double getPesoAtual() {
        return pesoAtual;
    }

    public double getVolumeAtual() {
        return volumeAtual;
    }

    public void setPesoAtual(double pesoAtual) {
        this.pesoAtual = pesoAtual;
    }

    public void setVolumeAtual(double volumeAtual) {
        this.volumeAtual = volumeAtual;
    }
}
