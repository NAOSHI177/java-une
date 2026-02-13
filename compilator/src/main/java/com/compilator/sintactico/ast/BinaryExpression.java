package com.compilator.sintactico.ast;

public class BinaryExpression implements Expression {
    private final String operator;
    private final Expression left;
    private final Expression right;

    public BinaryExpression(String operator, Expression left, Expression right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public String getOperator() {
        return operator;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
