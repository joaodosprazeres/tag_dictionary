package br.com.bb.dicttag.domain.tag;

import jakarta.validation.constraints.NotBlank;

public record DadosTag(Long id, @NotBlank String chave, @NotBlank String valor, String descricao, Boolean ativo) {

    public DadosTag(Tag tag){
        this(tag.getId(), tag.getChave(), tag.getValor(), tag.getDescricao(), tag.isAtivo());
    }
}
