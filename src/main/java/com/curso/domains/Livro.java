package com.curso.domains;

import com.curso.domains.dtos.LivroDTO;
import com.curso.domains.enums.Conservacao;
import com.curso.domains.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_livro")
    private Long idLivro;

    @NotNull @NotBlank
    private String titulo;

    @NotNull @NotBlank
    @Column(unique = true)
    private String isbn;

    @NotNull
    private int numeroPaginas;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    @NotNull
    @Digits(integer = 15, fraction = 3)
    private BigDecimal valorCompra;

    @ManyToOne
    @JoinColumn(name = "idautor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "ideditora")
    private Editora editora;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "status")
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    @JoinColumn(name = "conservacao")
    private Conservacao conservacao;


    public Livro() {
        this.valorCompra = BigDecimal.ZERO;
        this.status = Status.LENDO;
        this.conservacao = Conservacao.BOM;
    }

    public Livro(Long idLivro, String titulo, String isbn, int numeroPaginas, LocalDate dataCompra,
                 BigDecimal valorCompra, Autor autor, Editora editora, Status status, Conservacao conservacao) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.dataCompra = dataCompra;
        this.valorCompra = valorCompra;
        this.autor = autor;
        this.editora = editora;
        this.status = status;
        this.conservacao = conservacao;
    }

    public Livro(LivroDTO dto){
        this.idLivro = dto.getIdLivro();
        this.titulo = dto.getTitulo();
        this.isbn = dto.getIsbn();
        this.numeroPaginas = dto.getNumeroPaginas();
        this.dataCompra = dto.getDataCompra();
        this.valorCompra = dto.getValorCompra();
        this.autor = new Autor();
        this.autor.setId(dto.getAutor());
        this.editora = new Editora();
        this.editora.setId(dto.getEditora());
        this.status = Status.toEnum(dto.getStatus());
        this.conservacao = Conservacao.toEnum(dto.getConservacao());
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Long idLivro) {
        this.idLivro = idLivro;
    }

    public @NotNull @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotNull @NotBlank String titulo) {
        this.titulo = titulo;
    }

    public @NotNull @NotBlank String getIsbn() {
        return isbn;
    }

    public void setIsbn(@NotNull @NotBlank String isbn) {
        this.isbn = isbn;
    }


    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public @NotNull @Digits(integer = 15, fraction = 3) BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(@NotNull @Digits(integer = 15, fraction = 3) BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Conservacao getConservacao() {
        return conservacao;
    }

    public void setConservacao(Conservacao conservacao) {
        this.conservacao = conservacao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return idLivro == livro.idLivro && numeroPaginas == livro.numeroPaginas && Objects.equals(titulo, livro.titulo) && Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLivro, titulo, isbn, numeroPaginas);
    }
}