package br.com.bb.dicttag.controller;

import br.com.bb.dicttag.domain.tag.DadosTag;
import br.com.bb.dicttag.domain.tag.Tag;
import br.com.bb.dicttag.domain.tag.TagRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("tag")
public class TagController {
    @Autowired
    private TagRepository repository;

//    ModelMapper modelMapper = new ModelMapper();

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosTag dados, UriComponentsBuilder uriBuilder) {
        var tag = new Tag();
        tag.atualizarInformacoes(dados);
        repository.save(tag);

        var uri = uriBuilder.path("/api/{id}").buildAndExpand(tag.getId()).toUri() ;

        //dados = modelMapper.map(tag, DadosTag.class);
        return ResponseEntity.created(uri).body(dados);
    }

    @GetMapping
    public ResponseEntity<Page<DadosTag>> listar(@PageableDefault(size = 10, sort = {"chave"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosTag::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosTag dados) {
        var tag = repository.getReferenceById(dados.id());
        tag.atualizarInformacoes(dados);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var tag = repository.getReferenceById(id);
        tag.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var tag = repository.getReferenceById(id);
        var dados = new DadosTag(tag);
        return ResponseEntity.ok(dados);
    }
}
