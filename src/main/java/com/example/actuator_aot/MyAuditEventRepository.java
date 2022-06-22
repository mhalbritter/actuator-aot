package com.example.actuator_aot;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.stereotype.Component;

/**
 * @author Moritz Halbritter
 */
@Component
class MyAuditEventRepository implements AuditEventRepository {
	private final List<AuditEvent> auditEvents = new CopyOnWriteArrayList<>();

	@Override
	public void add(AuditEvent event) {
		this.auditEvents.add(event);
	}

	@Override
	public List<AuditEvent> find(String principal, Instant after, String type) {
		return auditEvents.stream()
				.filter(e -> {
					boolean matches = true;
					if (principal != null) {
						matches &= e.getPrincipal().equals(principal);
					}
					if (after != null) {
						matches &= e.getTimestamp().isAfter(after);
					}
					if (type != null) {
						matches &= e.getType().equals(type);
					}
					return matches;
				})
				.toList();
	}
}
