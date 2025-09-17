package com.github.devmix.sample.antlr4formula.nodes;

import com.github.devmix.sample.antlr4formula.FormulaContext;
import com.github.devmix.sample.antlr4formula.FormulaFunction;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;

/**
 * Represents a function node in the abstract syntax tree (AST) of a formula.
 *
 * <p>This class encapsulates a function call, including its name and arguments,
 * and provides functionality to evaluate the function within a given context.</p>
 *
 * @author Sergey Grachev
 */
@RequiredArgsConstructor
public class FunctionNode implements Node {
    /**
     * An empty array of doubles used when no values are needed.
     */
    private static final double[] EMPTY = new double[0];

    /**
     * The name of the function to be called.
     */
    private final String name;

    /**
     * The arguments passed to the function, represented as an array of {@link Node} objects.
     */
    private final Node[] arguments;

    /**
     * An array to store evaluated values of the arguments. This is initialized if there are any arguments.
     */
    private final @Nullable double[] values;

    /**
     * The {@link com.github.devmix.sample.antlr4formula.FormulaFunction} object representing the function to be called.
     * It is lazily initialized when first needed.
     */
    private @Nullable FormulaFunction fn = null;

    /**
     * Constructs a new FunctionNode with the specified name and arguments.
     *
     * <p>The values array is initialized if there are any arguments.</p>
     *
     * @param name The name of the function to be called.
     * @param args The arguments passed to the function, represented as an array of {@link Node} objects.
     */
    public FunctionNode(final String name, final Node... args) {
        this.name = name;
        this.arguments = args;
        this.values = arguments != null && arguments.length > 0 ? new double[args.length] : null;
    }

    /**
     * Evaluates the function within the given context.
     *
     * <p>If the function has not been resolved yet, it is looked up in the provided {@link FormulaContext}.
     * If the function is not found, an {@link IllegalStateException} is thrown.</p>
     *
     * <p>The arguments are evaluated and their values stored in the values array.
     * The function is then called with these values.</p>
     *
     * @param context The {@link FormulaContext} within which to evaluate the function.
     * @return The result of the function call as a double.
     */
    @Override
    public double evaluate(final FormulaContext context) {
        if (fn == null) {
            fn = context.findFunction(name);
            if (fn == null) {
                throw new IllegalStateException("Unknown function: " + name);
            }
        }

        if (values == null) {
            return fn.call(EMPTY);
        }

        int index = 0;
        for (final var argument : arguments) {
            values[index++] = argument.evaluate(context);
        }

        return fn.call(values);
    }
}