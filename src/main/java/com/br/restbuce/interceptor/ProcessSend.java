package com.br.restbuce.interceptor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.br.restbuce.exceptions.RestBuceException;

@SuppressWarnings("rawtypes")
public class ProcessSend implements ProcessExecute<ResponseEntity> {

	private ProcessRest processRest;

	public ProcessSend(ProcessRest processRest) {
		this.processRest = processRest;
	}

	@Override
	public ResponseEntity execute() {

		try {
			return send();
		} catch (HttpClientErrorException ex) {
			throw new RestBuceException(ex);
		}
	}

	@SuppressWarnings("unchecked")
	private ResponseEntity send() {

		RestTemplate restTemplate = new RestTemplate();

		return restTemplate.exchange(processRest.getLink(),
				processRest.getHttpMethod(), processRest.getEntity(),
				processRest.getClassReturn());
	}

}
