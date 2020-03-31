package br.com.caelum.filmes.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class BaseJpaCrudRepo<ENTITY, ID> implements CrudRepo<ENTITY, ID> {

  private final EntityManager entityManager;

  public BaseJpaCrudRepo(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  @Transactional
  public void saveAll(List<ENTITY> entities) {
      for (ENTITY entity : entities) {
        entityManager.persist(entity);
      }
  }

 }

