package com.br.restbuce.config;

import java.util.Collection;

public interface MultiBeanFactory<T> {
	T getObject(String name) throws ClassNotFoundException;

	Class<?> getObjectType();

	Collection<String[]> getNames();
}