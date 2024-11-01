package com.curso.services;


import com.curso.domains.Autor;
import com.curso.domains.Editora;
import com.curso.domains.Livro;
import com.curso.domains.dtos.LivroDTO;
import com.curso.repositories.AutorRepository;
import com.curso.repositories.EditoraRepository;
import com.curso.repositories.LivroRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepo;

    @Autowired
    private AutorRepository autorRepo;

    @Autowired
    private EditoraRepository editoraRepo;


    public List<LivroDTO> findAll(){

        return livroRepo.findAll().stream()
                .map(obj -> new LivroDTO(obj))
                .collect(Collectors.toList());
    }

    public Livro findById(Long id){
        Optional<Livro> obj = livroRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("livro não encontrado! Id:" + id));
    }

    public Livro findByIsbn(String isbn){
        Optional<Livro> obj = livroRepo.findByIsbn(isbn);
        return obj.orElseThrow(() -> new ObjectNotFoundException("livro não encontrado! Isbn:" + isbn));
    }

    public Livro create(LivroDTO dto){
        dto.setIdLivro(null);
        ValidaLivro(dto);
        Livro obj = new Livro(dto);
        return livroRepo.save(obj);
    }

    private void ValidaLivro(LivroDTO dto){
        Optional<Livro> obj = livroRepo.findByIsbn(dto.getIsbn());
        if(obj.isPresent() && obj.get().getIdLivro() != dto.getIdLivro()){
            throw new DataIntegrityViolationException("Isbn já cadastrado");
        }

        Optional<Autor> autor = autorRepo.findById(dto.getAutor());
        if (!autor.isPresent()){
            throw new DataIntegrityViolationException("Autor - " + dto.getAutor() + " não esta cadastrado!");
        }

        Optional<Editora> editora = editoraRepo.findById(dto.getEditora());
        if (!editora.isPresent()){
            throw new DataIntegrityViolationException("Editora - " + dto.getEditora() + " não esta cadastrado!");
        }
    }

    public Livro update(Long id, LivroDTO objDto){
        objDto.setIdLivro(id);
        Livro oldObj = findById(id);
        ValidaLivro(objDto);
        oldObj = new Livro(objDto);
        return livroRepo.save(oldObj);
    }

    public void delete(Long id){
        Livro obj = findById(id);
        livroRepo.deleteById(id);
    }

}