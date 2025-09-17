package com.github.devmix.sample.antlr4formula;

/**
 * Represents a functional interface for mathematical formulas that can be applied to an array of double values.
 *
 * <p>This interface is designed to be used with lambda expressions or method references, allowing
 * for the definition of custom formula functions that take an array of double arguments and return a double result.</p>
 *
 * @author Sergey Grachev
 */
@FunctionalInterface
public interface FormulaFunction {

    /**
     * Applies this function to the given array of double values.
     *
     * @param args an array of double values representing the input parameters for the formula
     * @return the result of applying this function to the input arguments as a double value
     */
    double call(double[] args);
}
