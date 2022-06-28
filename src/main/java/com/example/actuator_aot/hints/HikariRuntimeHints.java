package com.example.actuator_aot.hints;

import java.sql.Statement;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.util.ClassUtils;

/**
 * @author Moritz Halbritter
 */
public class HikariRuntimeHints implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        if (!ClassUtils.isPresent("com.zaxxer.hikari.HikariDataSource", classLoader)) {
            return;
        }
        hints.reflection().registerType(Statement[].class, hint -> {
        });
        hints.reflection().registerType(TypeReference.of("com.zaxxer.hikari.util.ConcurrentBag$IConcurrentBagEntry[]"), hint -> {
        });
    }

}
