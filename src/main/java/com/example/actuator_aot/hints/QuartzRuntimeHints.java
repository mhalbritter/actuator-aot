package com.example.actuator_aot.hints;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;
import org.springframework.util.ClassUtils;

/**
 * @author Moritz Halbritter
 */
public class QuartzRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        if (!ClassUtils.isPresent("org.quartz.Scheduler", classLoader)) {
            return;
        }
        hints.reflection().registerType(TypeReference.of("org.quartz.impl.StdSchedulerFactory"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("org.springframework.scheduling.quartz.ResourceLoaderClassLoadHelper"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS));
        hints.reflection().registerType(TypeReference.of("org.quartz.simpl.SimpleThreadPool"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS));
        hints.reflection().registerType(TypeReference.of("org.quartz.simpl.RAMJobStore"), hint -> hint.withMembers(MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS));
    }
}
