package br.com.caelum.filmes;

import org.springframework.data.repository.CrudRepository;

public interface FilmeRepository extends CrudRepository<Filme, Long> {
}
