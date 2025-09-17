package com.github.devmix.sample.antlr4formula.nodes;

import com.github.devmix.sample.antlr4formula.FormulaContext;
import lombok.Value;

/**
 * Represents a unary plus node in an abstract syntax tree (AST) for formula evaluation.
 * The unary plus operation does not change the value of its operand, but it can be used
 * to ensure that the operand is treated as a positive number.
 *
 * @author Sergey Grachev
 */
@Value
public class UnaryPlusNode implements Node {
    /**
     * The child node representing the operand to which the unary plus operation is applied.
     */
    private final Node child;

    /**
     * Evaluates the unary plus node by evaluating its child node and returning the result.
     * Since the unary plus does not change the value, this method simply returns the evaluated
     * value of the child node.
     *
     * @param context The formula context containing variables and their values.
     * @return The evaluated value of the child node.
     */
    @Override
    public double evaluate(final FormulaContext context) {
        return +child.evaluate(context);
    }
}
