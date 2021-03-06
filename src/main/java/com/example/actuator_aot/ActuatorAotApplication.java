package com.example.actuator_aot;

import java.util.List;

import com.example.actuator_aot.hints.FlywayRuntimeHints;
import com.example.actuator_aot.hints.HikariRuntimeHints;
import com.example.actuator_aot.hints.IntegrationRuntimeHints;
import com.example.actuator_aot.hints.LiquibaseRuntimeHints;
import com.example.actuator_aot.hints.QuartzRuntimeHints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.aot.AotDetector;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.NoOpCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.MapSession;

@SpringBootApplication
@ImportRuntimeHints({ FlywayRuntimeHints.class, HikariRuntimeHints.class, QuartzRuntimeHints.class, IntegrationRuntimeHints.class, LiquibaseRuntimeHints.class })
public class ActuatorAotApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActuatorAotApplication.class);

    public static void main(String[] args) {
        LOGGER.info("AOT enabled: " + AotDetector.useGeneratedArtifacts());
        SpringApplication application = new SpringApplication(ActuatorAotApplication.class);
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);
    }

    @Bean
    CommandLineRunner generateAuditEvent(ApplicationEventPublisher publisher) {
        return args -> publisher.publishEvent(new AuditApplicationEvent("mhalbritter", "type-1", "some-data", "some-more-data"));
    }

    @Bean
    CommandLineRunner populateSessions(FindByIndexNameSessionRepository<MapSession> repository) {
        return args -> {
            MapSession session = repository.createSession();
            session.setId("foo");
            repository.save(session);
        };
    }

    @Bean
    SimpleCacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        manager.setCaches(List.of(
                new ConcurrentMapCache("cache-1"),
                new NoOpCache("cache-2")
        ));
        return manager;
    }

}
