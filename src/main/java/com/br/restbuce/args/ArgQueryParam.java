package com.br.restbuce.args;

import java.util.HashMap;
import java.util.Map;

public class ArgQueryParam<T> {

	private Map<String, T> entity = new HashMap<String, T>();

	public void add(String key, T t){
		entity.put(key, t);
	}
	
	public T getEntity(String key) {
		return entity.get(key);
	}

	public int size() {
		return entity.size();
	}

	public Map<String, T> getEntity() {
		return entity;
	}
}
