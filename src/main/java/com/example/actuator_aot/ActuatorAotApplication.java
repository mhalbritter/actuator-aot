package com.example.actuator_aot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ActuatorAotApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ActuatorAotApplication.class);
		application.setApplicationStartup(new BufferingApplicationStartup(2048));
		application.run(args);
	}

	@Bean
	CommandLineRunner generateAuditEvent(ApplicationEventPublisher publisher) {
		return args -> publisher.publishEvent(new AuditApplicationEvent("mhalbritter", "type-1", "some-data", "some-more-data"));
	}

}
