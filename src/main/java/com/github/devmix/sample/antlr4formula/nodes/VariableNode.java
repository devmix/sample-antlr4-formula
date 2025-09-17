package com.github.devmix.sample.antlr4formula.nodes;

import com.github.devmix.sample.antlr4formula.FormulaContext;
import lombok.Value;

/**
 * Represents a variable node in an abstract syntax tree (AST) for formula evaluation.
 *
 * <p>This class is used to represent variables within a formula, which can be resolved
 * to their corresponding values using the provided {@link FormulaContext}.
 *
 * @author Sergey Grachev
 */
@Value
public class VariableNode implements Node {

    /**
     * The name of the variable represented by this node.
     */
    private final String name;

    /**
     * Evaluates the variable node using the given formula context.
     *
     * <p>This method retrieves the value of the variable from the provided {@link FormulaContext}.
     *
     * @param context the formula context containing variable values
     * @return the evaluated value of the variable
     */
    @Override
    public double evaluate(final FormulaContext context) {
        return context.var(name);
    }
}
