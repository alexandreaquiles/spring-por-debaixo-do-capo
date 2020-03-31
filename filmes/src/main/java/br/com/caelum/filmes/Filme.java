package br.com.caelum.filmes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Filme {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private Integer ano;

  private Double nota;

  @Deprecated
  public Filme() {
  }

  public Filme(String nome, int ano, double nota) {
    this.nome = nome;
    this.ano = ano;
    this.nota = nota;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Integer getAno() {
    return ano;
  }

  public void setAno(Integer ano) {
    this.ano = ano;
  }

  public Double getNota() {
    return nota;
  }

  public void setNota(Double nota) {
    this.nota = nota;
  }


}
