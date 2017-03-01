package com.br.restbuce.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("restbuce")
public class RestBuceProperties {

	private String pathScan;

	public String getPathScan() {
		return pathScan;
	}

	public void setPathScan(String pathScan) {
		this.pathScan = pathScan;
	}
}
