package model;

import java.util.ArrayList;
import java.util.List;

public class TagIoT {
    private String tagId;
    private String localizacaoAtual;

    // Lista global para rastrear todas as tags criadas
    private static List<TagIoT> todasAsTags = new ArrayList<>();

    public TagIoT(String tagId) {
        this.tagId = tagId;
        this.localizacaoAtual = "Desconhecida";
        todasAsTags.add(this); // Registra a tag ao ser criada
    }

    public String getTagId() {
        return tagId;
    }

    public String getLocalizacaoAtual() {
        return localizacaoAtual;
    }

    public void setLocalizacaoAtual(String localizacaoAtual) {
        this.localizacaoAtual = localizacaoAtual;
    }

    @Override
    public String toString() {
        return String.format("TagIoT[tagId=%s, localizacaoAtual=%s]", tagId, localizacaoAtual);
    }

    // Métodos estáticos para consulta

    public static TagIoT buscarPorTagId(String tagId) {
        for (TagIoT tag : todasAsTags) {
            if (tag.getTagId().equalsIgnoreCase(tagId)) {
                return tag;
            }
        }
        return null;
    }

    public static List<TagIoT> buscarPorLocalizacao(String localizacao) {
        List<TagIoT> resultado = new ArrayList<>();
        for (TagIoT tag : todasAsTags) {
            if (tag.getLocalizacaoAtual().equalsIgnoreCase(localizacao)) {
                resultado.add(tag);
            }
        }
        return resultado;
    }

    // Opcional: Listar todas as tags registradas
    public static List<TagIoT> listarTodasAsTags() {
        return new ArrayList<>(todasAsTags);
    }
}
