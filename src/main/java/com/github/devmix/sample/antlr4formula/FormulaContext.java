package com.github.devmix.sample.antlr4formula;

import lombok.Getter;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a context for evaluating formulas, containing variables and functions.
 *
 * @author Sergey Grachev
 */
@Getter
public final class FormulaContext {

    /**
     * The default value to return when a variable is not found in the context.
     */
    private static final Double DEFAULT_VALUE = (double) 0;

    /**
     * The current value of the formula context.
     */
    private double value;

    /**
     * A map containing variables and their corresponding values.
     */
    private final Map<String, Double> variables = new HashMap<>();

    /**
     * A map containing functions and their corresponding implementations.
     */
    private final Map<String, FormulaFunction> functions = new HashMap<>();

    /**
     * Constructs a new FormulaContext with the default value.
     */
    public FormulaContext() {
        this.value = DEFAULT_VALUE;
    }

    /**
     * Constructs a new FormulaContext with the specified initial value.
     *
     * @param value The initial value for the formula context.
     */
    public FormulaContext(final double value) {
        this.value = value;
    }

    /**
     * Adds a variable to the context with the given name and value.
     *
     * @param name  The name of the variable.
     * @param value The value of the variable.
     * @return This instance of FormulaContext for method chaining.
     */
    public FormulaContext addVariable(final String name, final double value) {
        variables.put(name, value);
        return this;
    }

    /**
     * Adds a function to the context with the given name and implementation.
     *
     * @param name The name of the function.
     * @param func The implementation of the function.
     * @return This instance of FormulaContext for method chaining.
     */
    public FormulaContext addFunction(final String name, final FormulaFunction func) {
        functions.put(name, func);
        return this;
    }

    /**
     * Calls a function in the context with the given name and arguments.
     *
     * @param name The name of the function to call.
     * @param args The arguments to pass to the function.
     * @return The result of the function call.
     * @throws IllegalStateException If the function is not found in the context.
     */
    public double call(final String name, final double... args) {
        final var fn = functions.get(name);
        if (fn == null) {
            throw new IllegalStateException("Unknown function: " + name);
        }
        return fn.call(args);
    }

    /**
     * Retrieves the value of a variable from the context.
     *
     * @param name The name of the variable to retrieve.
     * @return The value of the variable, or the default value if the variable is not found.
     */
    public double var(final String name) {
        final var v = variables.get(name);
        return v == null ? DEFAULT_VALUE : v;
    }

    /**
     * Finds a function in the context by its name.
     *
     * @param name The name of the function to find.
     * @return The function if found, or null if not found.
     */
    @Nullable
    public FormulaFunction findFunction(final String name) {
        return functions.get(name);
    }
}
