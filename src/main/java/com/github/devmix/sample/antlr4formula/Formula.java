package com.github.devmix.sample.antlr4formula;

/**
 * Represents a formula that can be evaluated within a given context.
 *
 * <p>This interface is designed to be used as a functional interface,
 * allowing for lambda expressions or method references to represent formulas.</p>
 *
 * @author Sergey Grachev
 */
@FunctionalInterface
public interface Formula {

    /**
     * Evaluates the formula using the provided context.
     *
     * @param context the context in which the formula should be evaluated
     * @return the result of the evaluation as a double value
     */
    double evaluate(FormulaContext context);
}
