package com.br.restbuce.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.br.restbuce.annotations.IgnoreRest;
import com.br.restbuce.interceptor.ContextStatic;
import com.br.restbuce.interceptor.RestClass;
import com.br.restbuce.repository.RestRepository;

@Configuration(value="configRestBuce")
@ImportAutoConfiguration(value={ContextStatic.class})
public class RestBuceConfig {

	@Bean
	@Primary
	@Order(-2147483598)
	public MultiBeanFactoryPostProcessor _1newBean() {
		return new MultiBeanFactoryPostProcessor();
	}
	
	@Bean
	@Order(-2147483598)
	public MultiBeanFactory<RestRepository> personFactory() throws IOException {
		Resource resource = new ClassPathResource("application.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		String restPath = (String) props.get("restbuce.pathscan");
		
		return new MultiBeanFactory<RestRepository>() {

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public RestRepository getObject(String name) throws ClassNotFoundException {
				Class clazz = Class.forName(name);
				return (RestRepository) RestClass.create(clazz);
			}

			public Class<?> getObjectType() {
				return RestRepository.class;
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Collection<String[]> getNames() {

				Reflections reflections = new Reflections(
						restPath == null ? "com.br.restbuce" : restPath);
				
				Set<Class<? extends RestRepository>> classes = reflections
						.getSubTypesOf(RestRepository.class);

				Collection<String[]> itens = new ArrayList<String[]>(classes.size());
				for (Class item : classes) {
					
					if( item.isAnnotationPresent(IgnoreRest.class) ){
						continue;
					}
					itens.add(new String[] {item.getCanonicalName(), item.getSimpleName()});
				}

				return itens;
			}

		};
	}

}
