package com.br.restbuce.args;

import java.util.ArrayList;
import java.util.List;

public class ArgPathParam<T> {
	private List<T> entity = new ArrayList<T>();

	public void add(T t){
		entity.add(t);
	}
	
	public T getEntity(int pos) {
		return entity.get(pos);
	}

	public int size() {
		return entity.size();
	}
}
