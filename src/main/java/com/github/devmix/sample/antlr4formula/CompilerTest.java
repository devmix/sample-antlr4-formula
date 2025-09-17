package com.github.devmix.sample.antlr4formula;

/**
 * @author Sergey Grachev
 */
public class CompilerTest {

    public static void main(String[] args) {
        var context = new FormulaContext(5);
        context.addFunction("FN1", a -> 5 * a[0]);
        context.addFunction("FN2", a -> 3 * a[0]);
        context.addVariable("VA1", 4);
        context.addVariable("VA2", 3);

        var expr = Compiler.compile("-1 + 2 * +3 + 5 + (VA1 * VA2) + FN1(FN2(2))");

        System.out.println(expr.evaluate(context));
    }
}
