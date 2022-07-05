package com.example.actuator_aot;

import java.util.Set;

import org.springframework.aot.hint.ExecutableHint;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.aot.hint.TypeHint;
import org.springframework.context.aot.BindingReflectionHintsRegistrar;

/**
 * @author Moritz Halbritter
 */
class Debug {
    public static void main(String[] args) {
        BindingReflectionHintsRegistrar registrar = new BindingReflectionHintsRegistrar();
        ReflectionHints hints = new ReflectionHints();
        registrar.registerReflectionHints(hints, R.class);

        hints.typeHints().forEach(Debug::printTypeHints);
    }

    private static void printTypeHints(TypeHint typeHint) {
        System.out.println(typeHint.getType());
        typeHint.methods().forEach(Debug::printExecutableHint);
    }

    private static void printExecutableHint(ExecutableHint executableHint) {
        System.out.println("  " + executableHint.getName() + "()");
    }

    record R(String field1, String field2) {}

    class A {
        public Set<B> getB() {
            return null;
        }
    }

    class B {
        public C getC() {
            return null;
        }
    }

    class C {
        public String getString() {
            return "";
        }
    }
}
