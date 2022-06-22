package com.example.actuator_aot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Component;

/**
 * @author Moritz Halbritter
 */
@Component
class MyHttpTraceRepository implements HttpTraceRepository {
	private final List<HttpTrace> httpTraces = new CopyOnWriteArrayList<>();

	@Override
	public List<HttpTrace> findAll() {
		return httpTraces;
	}

	@Override
	public void add(HttpTrace trace) {
		this.httpTraces.add(trace);
	}
}
