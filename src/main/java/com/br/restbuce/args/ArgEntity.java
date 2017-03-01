package com.br.restbuce.args;

public class ArgEntity<T> {

	private T entity;

	public ArgEntity(T entity) {
		this.entity = entity;
	}

	public T getEntity() {
		return entity;
	}
}
