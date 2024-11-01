package com.curso.domains.dtos;

import com.curso.domains.Autor;
import com.curso.domains.Editora;
import com.curso.domains.Livro;
import com.curso.domains.enums.Conservacao;
import com.curso.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDTO {

    private Long idLivro;

    @NotNull(message = "O campo titulo não pode ser nulo")
    @NotBlank(message = "campo titulo não pode estar vazio")
    private String titulo;

    @NotNull(message = "O campo isbn não pode ser nulo")
    @NotBlank(message = "campo isbn não pode estar vazio")
    private String isbn;

    @NotNull(message = "O campo numeroPaginas não pode ser nulo")
    private int numeroPaginas;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    @NotNull(message = "O campo valorCompra não pode ser nulo")
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorCompra;

    private int autor;

    private int editora;

    private int status;

    private int conservacao;

    public LivroDTO() {}

    public LivroDTO(Livro livro) {
        this.idLivro = livro.getIdLivro();
        this.titulo = livro.getTitulo();
        this.isbn = livro.getIsbn();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.dataCompra = livro.getDataCompra();
        this.valorCompra = livro.getValorCompra();
        this.autor = livro.getAutor().getId();
        this.editora = livro.getEditora().getId();
        this.status = livro.getStatus().getId();
        this.conservacao = livro.getConservacao().getId();
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public @NotNull(message = "O campo titulo não pode ser nulo") @NotBlank(message = "campo titulo não pode estar vazio") String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull(message = "O campo titulo não pode ser nulo") @NotBlank(message = "campo titulo não pode estar vazio") String titulo) {
        this.titulo = titulo;
    }

    public @NotNull(message = "O campo isbn não pode ser nulo") @NotBlank(message = "campo isbn não pode estar vazio") String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotNull(message = "O campo isbn não pode ser nulo") @NotBlank(message = "campo isbn não pode estar vazio") String isbn) {
        this.isbn = isbn;
    }

    @NotNull(message = "O campo numeroPaginas não pode ser nulo")
    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(@NotNull(message = "O campo numeroPaginas não pode ser nulo") @NotBlank(message = "campo numeroPaginas não pode estar vazio") int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public @NotNull(message = "O campo valorCompra não pode ser nulo") @Digits(integer = 15, fraction = 3) BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(@NotNull(message = "O campo valorCompra não pode ser nulo") @Digits(integer = 15, fraction = 3) BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public int getEditora() {
        return editora;
    }

    public void setEditora(int editora) {
        this.editora = editora;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getConservacao() {
        return conservacao;
    }

    public void setConservacao(int conservacao) {
        this.conservacao = conservacao;
    }
}