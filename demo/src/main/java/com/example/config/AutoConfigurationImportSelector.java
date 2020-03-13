package com.example.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

public class AutoConfigurationImportSelector implements ImportSelector, BeanClassLoaderAware {

	private ClassLoader classLoader;

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return SpringFactoriesLoader.loadFactoryNames(EnableMagicAutoConfiguration.class, classLoader)
				.toArray(new String[] {});
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

}
