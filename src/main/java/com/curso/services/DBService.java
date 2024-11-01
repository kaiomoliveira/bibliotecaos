package com.curso.services;

import com.curso.domains.Autor;
import com.curso.domains.Editora;
import com.curso.domains.Livro;
import com.curso.domains.enums.Conservacao;
import com.curso.domains.enums.Status;
import com.curso.repositories.AutorRepository;
import com.curso.repositories.EditoraRepository;
import com.curso.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private AutorRepository autorRepo;

    @Autowired
    private EditoraRepository editoraRepo;

    @Autowired
    private LivroRepository livroRepo;

    public void initDB() {

        Autor autor01 = new Autor(null, "J. K. Rowling", "699.536.128-89");
        Autor autor02 = new Autor(null, "Fernando Pessoa", "568.229.897-73");
        Autor autor03 = new Autor(null, "Machado de Assis", "550.328.347-34");

        autorRepo.save(autor01);
        autorRepo.save(autor02);
        autorRepo.save(autor03);

        Editora editora01 = new Editora(null, "Bloomsbury Publishing", "97.371.306/0001-96");
        Editora editora02 = new Editora(null, "Grupo Editorial Global", "36.639.152/0001-86");

        editoraRepo.save(editora01);
        editoraRepo.save(editora02);

        Livro livro01 = new Livro(null, "Harry Potter e o Prisioneiro de Azkaban\n", "978-8532512062", 348,
                LocalDate.now(), new BigDecimal("120"), autor01, editora01, Status.LENDO, Conservacao.EXELENTE);
        Livro livro02 = new Livro(null, "Dom Casmurro", "978-6586490084", 346,
                LocalDate.now(), new BigDecimal("100"), autor03, editora02, Status.LENDO, Conservacao.MARCASDEUSO);
        Livro livro03 = new Livro(null, "Poemas de Alberto Caeiro\n", "978-8415289159", 144 ,
                LocalDate.now(), new BigDecimal("80"), autor02, editora02, Status.LENDO, Conservacao.BOM);

        livroRepo.save(livro01);
        livroRepo.save(livro02);
        livroRepo.save(livro03);

    }
}