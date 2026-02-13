package com.compilator.sintactico.ast;

public class DeclarationStatement implements Statement {
    private final String name;
    private final Expression initializer;

    public DeclarationStatement(String name, Expression initializer) {
        this.name = name;
        this.initializer = initializer;
    }

    public String getName() {
        return name;
    }

    public Expression getInitializer() {
        return initializer;
    }
}
