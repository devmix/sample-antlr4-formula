package com.github.devmix.sample.antlr4formula.nodes;

import com.github.devmix.sample.antlr4formula.FormulaContext;
import lombok.Value;

/**
 * Represents a binary operation node in an abstract syntax tree (AST) for formula evaluation.
 *
 * <p>This class encapsulates two child nodes and an operator that defines the binary operation
 * to be performed between the values of these nodes.</p>
 *
 * @author Sergey Grachev
 */
@Value
public class BinaryNode implements Node {

    /**
     * Constant representing the addition (+) operator.
     */
    public static final byte PLUS = 0;

    /**
     * Constant representing the subtraction (-) operator.
     */
    public static final byte MINUS = 1;

    /**
     * Constant representing the multiplication (*) operator.
     */
    public static final byte MULTIPLY = 2;

    /**
     * Constant representing the division (/) operator.
     */
    public static final byte DIVIDE = 3;

    /**
     * The left operand node in the binary operation.
     */
    private final Node left;

    /**
     * The right operand node in the binary operation.
     */
    private final Node right;

    /**
     * The operator defining the binary operation to be performed between the left and right nodes.
     */
    private final byte operator;

    /**
     * Constructs a new BinaryNode with the specified left and right child nodes and operator.
     *
     * @param left     the left operand node
     * @param operator the operator defining the binary operation
     * @param right    the right operand node
     */
    public BinaryNode(final Node left, final byte operator, final Node right) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    /**
     * Evaluates the binary operation represented by this node using the provided formula context.
     *
     * @param context the formula context containing variable values and other evaluation data
     * @return the result of the binary operation as a double value
     * @throws RuntimeException if an unknown operator is encountered
     */
    @Override
    public double evaluate(final FormulaContext context) {
        final var lVal = left.evaluate(context);
        final var rVal = right.evaluate(context);

        return switch (operator) {
            case PLUS -> lVal + rVal;
            case MINUS -> lVal - rVal;
            case MULTIPLY -> lVal * rVal;
            case DIVIDE -> lVal / rVal;
            default -> throw new RuntimeException("Unknown operator: " + operator);
        };
    }
}