package br.com.caelum.filmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FilmesApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext applicationContext = SpringApplication.run(FilmesApplication.class, args);
//    EntityManager entityManager = applicationContext.getBean(EntityManager.class);
//    RepositoryFactorySupport repositoryFactorySupport = new JpaRepositoryFactory(entityManager);
//    FilmeRepository filmeRepository = repositoryFactorySupport.getRepository(FilmeRepository.class);
//    System.out.println(filmeRepository);
  }

}

