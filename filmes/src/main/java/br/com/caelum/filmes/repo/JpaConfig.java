package br.com.caelum.filmes.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@EnableJpaRepositories
//@EnableTransactionManagement
@Import(JpaRepoRegistrar.class)
class JpaConfig {

//  @Value("${spring.datasource.url}")
//  private String dataSourceUrl;
//
//  @Value("${spring.datasource.username}")
//  private String dataSourceUsername;
//
//  @Value("${spring.datasource.password}")
//  private String dataSourcePassword;
//
//  @Bean
//  public DataSource dataSource() {
//    MysqlDataSource dataSource = new MysqlDataSource();
//    dataSource.setUrl(dataSourceUrl);
//    dataSource.setUser(dataSourceUsername);
//    dataSource.setPassword(dataSourcePassword);
//    return dataSource;
//  }
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//
//    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    vendorAdapter.setGenerateDdl(true);
//
//    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//    factory.setJpaVendorAdapter(vendorAdapter);
//    factory.setPackagesToScan("br.com.caelum.filmes");
//    factory.setDataSource(dataSource);
//    return factory;
//  }
//
//  @Bean
//  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//    JpaTransactionManager txManager = new JpaTransactionManager();
//    txManager.setEntityManagerFactory(entityManagerFactory);
//    return txManager;
//  }

}
