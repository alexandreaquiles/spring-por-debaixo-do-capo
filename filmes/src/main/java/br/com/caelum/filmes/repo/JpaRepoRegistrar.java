package br.com.caelum.filmes.repo;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Set;

public class JpaRepoRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

  private Environment environment;
  private ResourceLoader resourceLoader;

  @Override
  public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                      BeanDefinitionRegistry registry,
                                      BeanNameGenerator importBeanNameGenerator) {
    System.out.println(importingClassMetadata);
    System.out.println(registry);
    System.out.println(importBeanNameGenerator);

    ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false) {
      @Override
      protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return !CrudRepo.class.getName().equals(beanDefinition.getBeanClassName()) && !BaseJpaCrudRepo.class.getName().equals(beanDefinition.getBeanClassName());
      }
    };
    scanner.setResourceLoader(resourceLoader);
    scanner.setEnvironment(environment);
    scanner.addIncludeFilter(new AssignableTypeFilter(CrudRepo.class));
    String basePackage = ClassUtils.getPackageName(importingClassMetadata.getClassName());
    Set<BeanDefinition> components = scanner.findCandidateComponents(basePackage);
    System.out.println(components);
    for (BeanDefinition candidate : components) {
      BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition("br.com.caelum.filmes.repo.CrudRepoFactoryBean");
      beanDefinitionBuilder.getRawBeanDefinition().setSource(candidate.getSource());
      beanDefinitionBuilder.addConstructorArgValue(candidate.getBeanClassName());
      AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
      beanDefinition.setAttribute("factoryBeanObjectType", candidate.getBeanClassName());
      String beanName = importBeanNameGenerator.generateBeanName(beanDefinition, registry);
      registry.registerBeanDefinition(beanName, beanDefinition);
    }

    //ClassPathBeanDefinitionScanner#scanCandidateComponents:419
    // ResourcePatternResolver == AnnotationConfigServletWebServerApplicationContext
    //PathMatchingResourcePatternResolver é o default
    //ClassLoaderFilesResourcePatternResolver#getResources:112
    // patternResolverDelegate = ServletContextResourcePatternResolver que é filha PathMatchingResourcePatternResolver
    //ServletContextResourcePatternResolver#findPathMatchingResources (recursivo) que é de PathMatchingResourcePatternResolver, na real
    //classpath*:br/com/caelum/filmes/**/*.class
    // tira **/*.class
    //PathMatchingResourcePatternResolver#findAllClassPathResources com classpath*:br/com/caelum/filmes/
    //PathMatchingResourcePatternResolver#doFindAllClassPathResources:338
    //file:/home/alexandre/IdeaProjects/filmes/target/classes/br/com/caelum/filmes/

//    try {
//      Enumeration<URL> resources = resourceLoader.getClassLoader().getResources("/");
//      while (resources.hasMoreElements()) {
//        URL url = resources.nextElement();
//        System.out.println(url);
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

  }

  @Override
  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void setResourceLoader(ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }
}
