package com.example.actuator_aot.hints;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.util.ClassUtils;

/**
 * @author Moritz Halbritter
 */
public class FlywayRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        if (!ClassUtils.isPresent("org.flywaydb.core.Flyway", classLoader)) {
            return;
        }
        hints.reflection().registerType(TypeReference.of("org.slf4j.Logger"), hint -> {
        });
        hints.reflection().registerType(TypeReference.of("org.slf4j.impl.StaticLoggerBinder"), hint -> {
        });
        hints.reflection().registerType(TypeReference.of("org.flywaydb.core.internal.logging.slf4j.Slf4jLogCreator"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS));
        hints.resources().registerPattern("org/flywaydb/core/internal/version.txt");
        hints.resources().registerPattern("db/migration/*.sql");
    }
}
