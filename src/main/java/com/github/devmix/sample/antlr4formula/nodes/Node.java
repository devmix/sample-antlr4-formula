package com.github.devmix.sample.antlr4formula.nodes;

import com.github.devmix.sample.antlr4formula.Formula;

/**
 * Represents a node in an abstract syntax tree (AST) for formulas.
 *
 * <p>This interface extends the {@link Formula} interface, indicating that each node
 * in the AST can be evaluated as part of a formula.</p>
 *
 * @author Sergey Grachev
 */
public interface Node extends Formula {
}
