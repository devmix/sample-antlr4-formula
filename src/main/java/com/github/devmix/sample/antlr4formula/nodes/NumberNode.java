package com.github.devmix.sample.antlr4formula.nodes;

import com.github.devmix.sample.antlr4formula.FormulaContext;
import lombok.Value;

/**
 * Represents a node in the abstract syntax tree (AST) that holds a numeric value.
 *
 * @author Sergey Grachev
 */
@Value
public class NumberNode implements Node {

    /**
     * The numeric value stored in this node.
     */
    private final double value;

    /**
     * Evaluates the node, which simply returns the stored numeric value.
     *
     * @param context the formula evaluation context (not used for NumberNode)
     * @return the numeric value of this node
     */
    @Override
    public double evaluate(final FormulaContext context) {
        return value;
    }
}
