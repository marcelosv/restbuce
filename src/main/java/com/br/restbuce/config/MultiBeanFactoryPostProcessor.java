package com.br.restbuce.config;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class MultiBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	
	
	@SuppressWarnings("rawtypes")
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
		Map<String, MultiBeanFactory> factories = beanFactory
				.getBeansOfType(MultiBeanFactory.class);

		for (Map.Entry<String, MultiBeanFactory> entry : factories.entrySet()) {
			MultiBeanFactory factoryBean = entry.getValue();

			for (Object item : factoryBean.getNames()) {

				String[] itemString = (String[]) item;

				String name = itemString[0];
				String nameBean = itemString[1];

				BeanDefinition definition = BeanDefinitionBuilder
						.genericBeanDefinition(factoryBean.getObjectType())
						.setScope(BeanDefinition.SCOPE_SINGLETON)
						.setFactoryMethod("getObject")
						.addConstructorArgValue(name).getBeanDefinition();

				definition.setFactoryBeanName(entry.getKey());
				registry.registerBeanDefinition(nameBean, definition);

			}
		}
	}
	
}