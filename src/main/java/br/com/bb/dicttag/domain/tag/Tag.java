package br.com.bb.dicttag.domain.tag;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Tag")
@Table(name = "tags")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String chave;

    private String valor;

    private String descricao;

    private boolean ativo;

    public void excluir() {
        this.ativo = false;
    }

    public void atualizarInformacoes(DadosTag dados) {
        this.id = dados.id();
        this.chave = dados.chave();
        this.valor = dados.valor();
        this.descricao = dados.descricao();
        this.ativo = dados.ativo();
    }
}
