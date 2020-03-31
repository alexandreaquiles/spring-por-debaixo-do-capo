package br.com.caelum.filmes.repo;

import java.util.List;

public interface CrudRepo<ENTITY, ID> {

  void saveAll(List<ENTITY> filmes);

}
