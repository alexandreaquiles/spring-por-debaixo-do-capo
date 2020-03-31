package br.com.caelum.filmes.repo;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.util.ClassTypeInformation;
import org.springframework.data.util.TypeInformation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

public class CrudRepoFactoryBean<T extends CrudRepo> implements FactoryBean<T>, BeanClassLoaderAware, ApplicationContextAware {

  private final Class<? extends T> repositoryInterface;
  private TypeInformation<?> domainType;
  private TypeInformation<?> idType;
  private EntityManager entityManager;
  private ClassLoader classLoader;
  private ApplicationContext applicationContext;

  public CrudRepoFactoryBean(Class<? extends T> repositoryInterface) {
    this.repositoryInterface = repositoryInterface;
  }

  @Override
  public T getObject() throws Exception {
    List<TypeInformation<?>> typeArguments = ClassTypeInformation.from(repositoryInterface)
      .getRequiredSuperTypeInformation(CrudRepo.class)
      .getTypeArguments();
    this.domainType = typeArguments.get(0);
    this.idType = typeArguments.get(1);

    Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(repositoryInterface);
    System.out.println("typeVariableMap = " + typeVariableMap);

    BaseJpaCrudRepo target = applicationContext.getBean(BaseJpaCrudRepo.class);
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(target);
    proxyFactory.setInterfaces(repositoryInterface, CrudRepo.class);
    return (T) proxyFactory.getProxy(classLoader);
  }

  @Override
  public Class<?> getObjectType() {
    return repositoryInterface;
  }

  @PersistenceContext
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public void setBeanClassLoader(ClassLoader classLoader) {
    this.classLoader = classLoader;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
