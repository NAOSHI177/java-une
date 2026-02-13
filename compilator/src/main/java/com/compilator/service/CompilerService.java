package com.compilator.service;

import com.compilator.intermedio.IntermediateCodeGenerator;
import com.compilator.lexico.LexicalAnalyzer;
import com.compilator.lexico.Token;
import com.compilator.semantico.SemanticAnalyzer;
import com.compilator.sintactico.Parser;
import com.compilator.sintactico.ast.AssignmentStatement;
import com.compilator.sintactico.ast.BinaryExpression;
import com.compilator.sintactico.ast.DeclarationStatement;
import com.compilator.sintactico.ast.Expression;
import com.compilator.sintactico.ast.NumberExpression;
import com.compilator.sintactico.ast.PrintStatement;
import com.compilator.sintactico.ast.Program;
import com.compilator.sintactico.ast.Statement;
import com.compilator.sintactico.ast.VariableExpression;
import java.util.List;
import java.util.stream.Collectors;

public class CompilerService {

    public CompileResult compile(String source) {
        try {
            LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer(source);
            List<Token> tokens = lexicalAnalyzer.scanTokens();

            Parser parser = new Parser(tokens);
            Program program = parser.parse();

            SemanticAnalyzer semanticAnalyzer = new SemanticAnalyzer();
            semanticAnalyzer.analyze(program);

            IntermediateCodeGenerator intermediateCodeGenerator = new IntermediateCodeGenerator();
            List<String> instructions = intermediateCodeGenerator.generate(program);

            String tokenOutput = tokens.stream().map(Token::toString).collect(Collectors.joining("\n"));
            String astOutput = toAstText(program);
            String intermediateOutput = String.join("\n", instructions);

            return new CompileResult(true, tokenOutput, astOutput, intermediateOutput, "Compilacion exitosa.");
        } catch (RuntimeException ex) {
            return new CompileResult(false, "", "", "", ex.getMessage());
        }
    }

    private String toAstText(Program program) {
        StringBuilder sb = new StringBuilder();
        sb.append("Program\n");
        for (Statement statement : program.getStatements()) {
            if (statement instanceof DeclarationStatement) {
                DeclarationStatement declaration = (DeclarationStatement) statement;
                sb.append("  Declaration ").append(declaration.getName());
                if (declaration.getInitializer() != null) {
                    sb.append(" = ").append(expressionToText(declaration.getInitializer()));
                }
                sb.append("\n");
            } else if (statement instanceof AssignmentStatement) {
                AssignmentStatement assignment = (AssignmentStatement) statement;
                sb.append("  Assignment ").append(assignment.getName())
                    .append(" = ").append(expressionToText(assignment.getExpression())).append("\n");
            } else if (statement instanceof PrintStatement) {
                PrintStatement print = (PrintStatement) statement;
                sb.append("  Print ").append(expressionToText(print.getExpression())).append("\n");
            }
        }
        return sb.toString().trim();
    }

    private String expressionToText(Expression expression) {
        if (expression instanceof NumberExpression) {
            return Integer.toString(((NumberExpression) expression).getValue());
        }
        if (expression instanceof VariableExpression) {
            return ((VariableExpression) expression).getName();
        }
        BinaryExpression binary = (BinaryExpression) expression;
        return "(" + expressionToText(binary.getLeft()) + " " + binary.getOperator() + " "
            + expressionToText(binary.getRight()) + ")";
    }
}
