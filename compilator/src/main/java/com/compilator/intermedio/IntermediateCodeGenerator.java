package com.compilator.intermedio;

import com.compilator.sintactico.ast.AssignmentStatement;
import com.compilator.sintactico.ast.BinaryExpression;
import com.compilator.sintactico.ast.DeclarationStatement;
import com.compilator.sintactico.ast.Expression;
import com.compilator.sintactico.ast.NumberExpression;
import com.compilator.sintactico.ast.PrintStatement;
import com.compilator.sintactico.ast.Program;
import com.compilator.sintactico.ast.Statement;
import com.compilator.sintactico.ast.VariableExpression;
import java.util.ArrayList;
import java.util.List;

public class IntermediateCodeGenerator {
    private final List<String> instructions = new ArrayList<>();
    private int tempCounter = 0;

    public List<String> generate(Program program) {
        for (Statement statement : program.getStatements()) {
            if (statement instanceof DeclarationStatement) {
                generateDeclaration((DeclarationStatement) statement);
            } else if (statement instanceof AssignmentStatement) {
                generateAssignment((AssignmentStatement) statement);
            } else if (statement instanceof PrintStatement) {
                String value = generateExpression(((PrintStatement) statement).getExpression());
                instructions.add("PRINT " + value);
            }
        }
        return instructions;
    }

    private void generateDeclaration(DeclarationStatement declaration) {
        instructions.add("DECLARE " + declaration.getName());
        if (declaration.getInitializer() != null) {
            String value = generateExpression(declaration.getInitializer());
            instructions.add(declaration.getName() + " = " + value);
        }
    }

    private void generateAssignment(AssignmentStatement assignment) {
        String value = generateExpression(assignment.getExpression());
        instructions.add(assignment.getName() + " = " + value);
    }

    private String generateExpression(Expression expression) {
        if (expression instanceof NumberExpression) {
            return Integer.toString(((NumberExpression) expression).getValue());
        }

        if (expression instanceof VariableExpression) {
            return ((VariableExpression) expression).getName();
        }

        BinaryExpression binary = (BinaryExpression) expression;
        String left = generateExpression(binary.getLeft());
        String right = generateExpression(binary.getRight());
        String temp = newTemp();
        instructions.add(temp + " = " + left + " " + binary.getOperator() + " " + right);
        return temp;
    }

    private String newTemp() {
        return "t" + (++tempCounter);
    }
}
