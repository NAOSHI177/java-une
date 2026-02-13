package com.compilator.semantico;

import com.compilator.sintactico.ast.AssignmentStatement;
import com.compilator.sintactico.ast.BinaryExpression;
import com.compilator.sintactico.ast.DeclarationStatement;
import com.compilator.sintactico.ast.Expression;
import com.compilator.sintactico.ast.NumberExpression;
import com.compilator.sintactico.ast.PrintStatement;
import com.compilator.sintactico.ast.Program;
import com.compilator.sintactico.ast.Statement;
import com.compilator.sintactico.ast.VariableExpression;
import java.util.HashSet;
import java.util.Set;

public class SemanticAnalyzer {
    private final Set<String> symbols = new HashSet<>();

    public void analyze(Program program) {
        for (Statement statement : program.getStatements()) {
            if (statement instanceof DeclarationStatement) {
                analyzeDeclaration((DeclarationStatement) statement);
            } else if (statement instanceof AssignmentStatement) {
                analyzeAssignment((AssignmentStatement) statement);
            } else if (statement instanceof PrintStatement) {
                analyzeExpression(((PrintStatement) statement).getExpression());
            }
        }
    }

    private void analyzeDeclaration(DeclarationStatement declaration) {
        if (symbols.contains(declaration.getName())) {
            throw new SemanticException("[Semantico] Variable ya declarada: " + declaration.getName());
        }
        symbols.add(declaration.getName());
        if (declaration.getInitializer() != null) {
            analyzeExpression(declaration.getInitializer());
        }
    }

    private void analyzeAssignment(AssignmentStatement assignment) {
        if (!symbols.contains(assignment.getName())) {
            throw new SemanticException("[Semantico] Variable no declarada: " + assignment.getName());
        }
        analyzeExpression(assignment.getExpression());
    }

    private void analyzeExpression(Expression expression) {
        if (expression instanceof NumberExpression) {
            return;
        }

        if (expression instanceof VariableExpression) {
            String name = ((VariableExpression) expression).getName();
            if (!symbols.contains(name)) {
                throw new SemanticException("[Semantico] Uso de variable no declarada: " + name);
            }
            return;
        }

        if (expression instanceof BinaryExpression) {
            BinaryExpression binary = (BinaryExpression) expression;
            analyzeExpression(binary.getLeft());
            analyzeExpression(binary.getRight());
        }
    }
}
