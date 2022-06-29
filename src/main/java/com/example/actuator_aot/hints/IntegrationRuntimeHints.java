package com.example.actuator_aot.hints;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.util.ClassUtils;

/**
 * @author Moritz Halbritter
 */
public class IntegrationRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        if (!ClassUtils.isPresent("org.springframework.integration.IntegrationPattern", classLoader)) {
            return;
        }

        hints.reflection().registerType(TypeReference.of("org.springframework.integration.context.IntegrationContextUtils"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS));
        hints.reflection().registerType(TypeReference.of("org.springframework.beans.factory.config.BeanExpressionContext"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_METHODS));
        hints.reflection().registerType(TypeReference.of("java.util.Properties"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_METHODS));
    }
}
