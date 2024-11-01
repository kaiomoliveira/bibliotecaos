package com.curso.resources;


import com.curso.domains.Editora;
import com.curso.domains.Livro;
import com.curso.domains.dtos.AutorDTO;
import com.curso.domains.dtos.EditoraDTO;
import com.curso.domains.dtos.LivroDTO;
import com.curso.services.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/livro")
public class LivroResource {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(){
        return ResponseEntity.ok().body(livroService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id){
        Livro obj = this.livroService.findById(id);
        return  ResponseEntity.ok().body(new LivroDTO(obj));
    }

    @GetMapping(value = "/isbn/{isbn}")
    public ResponseEntity<LivroDTO> findByIsbn(@PathVariable String isbn){
        Livro obj = this.livroService.findByIsbn(isbn);
        return  ResponseEntity.ok().body(new LivroDTO(obj));
    }

    @PostMapping
    public ResponseEntity<LivroDTO> create(@Valid @RequestBody LivroDTO dto){
        Livro livro = livroService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(livro.getIdLivro()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> update(@PathVariable Long id, @Valid @RequestBody LivroDTO objDto){
        Livro Obj = livroService.update(id, objDto);
        return ResponseEntity.ok().body(new LivroDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LivroDTO> delete(@PathVariable Long id){
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}