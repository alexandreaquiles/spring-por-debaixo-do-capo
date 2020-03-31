package br.com.caelum.filmes;

import br.com.caelum.filmes.repo.FilmeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Component
@Transactional
public class PopuladorDeFilmes {

  @Autowired
  private FilmeRepository repository;

  @Autowired
  private FilmeRepo repo;

  @PostConstruct
  public void popula() throws Exception {

    List<Filme> filmes = Arrays.asList(
      new Filme("Um Sonho de Liberdade", 1994, 9.2),
      new Filme("O Poderoso Chefão", 1972, 9.1),
      new Filme("O Poderoso Chefão II", 1974, 9.0),
      new Filme("Batman: O Cavaleiro das Trevas", 2008, 9.0)
    );

    //repo.saveAll(filmes);
    repo.saveAll(filmes);
  }
}
