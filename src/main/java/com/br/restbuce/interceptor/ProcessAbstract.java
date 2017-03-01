package com.br.restbuce.interceptor;

public abstract class ProcessAbstract {

	@SuppressWarnings("rawtypes")
	protected Object getArg(Class clazz, Object[] args) {

		if (args == null) {
			return null;
		}

		for (Object arg : args) {
			if (arg.getClass().equals(clazz)) {
				return arg;
			}
		}

		return null;
	}

	protected String getFirstChar(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}
		return Character.toString(value.charAt(0));
	}

	protected String getLastChar(String value) {
		return Character.toString(value.charAt(value.length() - 1));
	}
}
