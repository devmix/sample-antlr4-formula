package com.github.devmix.sample.antlr4formula;

import com.github.devmix.sample.antlr4formula.nodes.BinaryNode;
import com.github.devmix.sample.antlr4formula.nodes.FunctionNode;
import com.github.devmix.sample.antlr4formula.nodes.Node;
import com.github.devmix.sample.antlr4formula.nodes.NumberNode;
import com.github.devmix.sample.antlr4formula.nodes.UnaryMinusNode;
import com.github.devmix.sample.antlr4formula.nodes.UnaryPlusNode;
import com.github.devmix.sample.antlr4formula.nodes.VariableNode;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Compiler class responsible for parsing and compiling effort formula expressions.
 *
 * @author Sergey Grachev
 */
public class Compiler extends EffortFormulaExprBaseVisitor<Node> {

    /**
     * Compiles the given expression string into an abstract syntax tree (AST) node.
     *
     * @param expression The effort formula expression to compile.
     * @return The root node of the compiled AST.
     */
    public static Node compile(final String expression) {
        final var tokens = new CommonTokenStream(new EffortFormulaExprLexer(CharStreams.fromString(expression)));
        final var parser = new EffortFormulaExprParser(tokens);
        return new Compiler().visit(parser.expression());
    }

    /**
     * Visits an expression context and constructs the corresponding AST node.
     *
     * @param ctx The expression context to visit.
     * @return The constructed AST node for the given expression.
     */
    @Override
    public Node visitExpression(final EffortFormulaExprParser.ExpressionContext ctx) {
        if (ctx.atom() != null) {
            if (!ctx.PLUS().isEmpty()) {
                return new UnaryPlusNode(visit(ctx.atom()));
            }
            if (!ctx.MINUS().isEmpty()) {
                return new UnaryMinusNode(visit(ctx.atom()));
            }
        }
        if (!ctx.PLUS().isEmpty()) {
            return new BinaryNode(
                    visit(ctx.expression(0)), BinaryNode.PLUS, visit(ctx.expression(1)));
        }
        if (!ctx.MINUS().isEmpty()) {
            return new BinaryNode(
                    visit(ctx.expression(0)), BinaryNode.MINUS, visit(ctx.expression(1)));
        }
        if (ctx.MUL() != null) {
            return new BinaryNode(
                    visit(ctx.expression(0)), BinaryNode.MULTIPLY, visit(ctx.expression(1)));
        }
        if (ctx.DIV() != null) {
            return new BinaryNode(
                    visit(ctx.expression(0)), BinaryNode.DIVIDE, visit(ctx.expression(1)));
        }
        if (ctx.LPAREN() != null) {
            return visit(ctx.expression(0));
        }
        return super.visitExpression(ctx);
    }

    /**
     * Visits an atom context and constructs the corresponding AST node.
     *
     * @param ctx The atom context to visit.
     * @return The constructed AST node for the given atom.
     */
    @Override
    public Node visitAtom(final EffortFormulaExprParser.AtomContext ctx) {
        if (ctx.NUMBER() != null) {
            return new NumberNode(Double.parseDouble(ctx.NUMBER().getText()));
        }
        if (ctx.VARIABLE() != null) {
            return new VariableNode(ctx.VARIABLE().getText());
        }
        return super.visitAtom(ctx);
    }

    /**
     * Visits a function context and constructs the corresponding AST node.
     *
     * @param ctx The function context to visit.
     * @return The constructed AST node for the given function.
     */
    @Override
    public Node visitFunc(final EffortFormulaExprParser.FuncContext ctx) {
        final var fnName = ctx.VARIABLE().getText();
        final var contexts = ctx.expression();
        final var args = new Node[contexts.size()];
        int index = 0;
        for (final var arg : contexts) {
            args[index++] = visit(arg);
        }
        return new FunctionNode(fnName, args);
    }
}
