package com.github.devmix.sample.antlr4formula.nodes;

import com.github.devmix.sample.antlr4formula.FormulaContext;
import lombok.Value;

/**
 * Represents a unary minus operation in an abstract syntax tree (AST).
 *
 * <p>This node contains a single child node, which represents the operand to be negated.
 * When evaluated, this node returns the negative of its child's value.</p>
 *
 * @author Sergey Grachev
 */
@Value
public class UnaryMinusNode implements Node {
    /**
     * The child node representing the operand to be negated.
     */
    private final Node child;

    /**
     * Evaluates the unary minus operation using the provided formula context.
     *
     * <p>This method evaluates the child node and returns its value negated.</p>
     *
     * @param context the formula context used for evaluation
     * @return the negative of the evaluated child node's value
     */
    @Override
    public double evaluate(final FormulaContext context) {
        return -child.evaluate(context);
    }
}
