// Classe Armazem modificada
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Armazem {
    private String nome;
    private String morada;
    private double capacidadePeso;
    private double capacidadeVolume;
    private double pesoAtual;
    private double volumeAtual;
    private boolean isReciclagem;
    private List<Mercadoria> mercadorias;

    public Armazem(String nome, String morada, double capacidadePeso, double capacidadeVolume, boolean isReciclagem) {
        this.nome = nome;
        this.morada = morada;
        this.capacidadePeso = capacidadePeso;
        this.capacidadeVolume = capacidadeVolume;
        this.isReciclagem = isReciclagem;
        this.mercadorias = new ArrayList<>();
        this.pesoAtual = 0;
        this.volumeAtual = 0;
    }

    public boolean adicionarMercadoria(Mercadoria mercadoria) {

        boolean temEspacoSuficiente = pesoAtual + mercadoria.getPeso() <= capacidadePeso && volumeAtual + mercadoria.getVolume() <= capacidadeVolume;
   
        if (temEspacoSuficiente && (isReciclagem || mercadoria.isValid())) {
          
            mercadorias.add(mercadoria);
    
            pesoAtual += mercadoria.getPeso();
            volumeAtual += mercadoria.getVolume();
    
            mercadoria.getTagIoT().setLocalizacaoAtual("Armazém: " + nome);
    
            return true; 
        }
        System.out.println("Espaço não suficiente no Armazem:"+nome);
        return false; 
    }
    

    public boolean removerMercadoria(String mercadoriaId) {
        for (Mercadoria m : mercadorias) {
            if (m.getId().equals(mercadoriaId)) {
                mercadorias.remove(m);
                pesoAtual -= m.getPeso();
                volumeAtual -= m.getVolume();
                m.getTagIoT().setLocalizacaoAtual("Fora do armazém : " + nome);
                return true;
            }
        }
        return false;
    }

    public List<Mercadoria> listarMercadorias() {
        return mercadorias;
    }

    public List<Mercadoria> buscarPorEstado(String estado) {
        if ("válidas".equalsIgnoreCase(estado)) {
            return mercadorias.stream().filter(Mercadoria::isValid).collect(Collectors.toList());
        } else if ("perecíveis vencidas".equalsIgnoreCase(estado)) {
            return mercadorias.stream()
                              .filter(m -> "perecível".equalsIgnoreCase(m.getTipo()) && !m.isValid())
                              .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Armazem[nome=%s, morada=%s, capacidadePeso=%.2f, capacidadeVolume=%.2f, mercadorias=%d]", 
                             nome, morada, capacidadePeso, capacidadeVolume, mercadorias.size());
    }
}
