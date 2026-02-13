package com.compilator.sintactico;

import com.compilator.lexico.Token;
import com.compilator.lexico.TokenType;
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

public class Parser {
    private final List<Token> tokens;
    private int current = 0;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Program parse() {
        List<Statement> statements = new ArrayList<>();
        while (!isAtEnd()) {
            statements.add(statement());
        }
        return new Program(statements);
    }

    private Statement statement() {
        if (match(TokenType.KEYWORD_INT)) {
            return declaration();
        }
        if (match(TokenType.KEYWORD_PRINT)) {
            return printStatement();
        }
        if (check(TokenType.IDENTIFIER)) {
            return assignment();
        }
        throw error(peek(), "Se esperaba una sentencia valida.");
    }

    private Statement declaration() {
        Token name = consume(TokenType.IDENTIFIER, "Se esperaba identificador en declaracion.");
        Expression initializer = null;
        if (match(TokenType.ASSIGN)) {
            initializer = expression();
        }
        consume(TokenType.SEMICOLON, "Se esperaba ';' al final de declaracion.");
        return new DeclarationStatement(name.getLexeme(), initializer);
    }

    private Statement assignment() {
        Token name = consume(TokenType.IDENTIFIER, "Se esperaba identificador en asignacion.");
        consume(TokenType.ASSIGN, "Se esperaba '=' en asignacion.");
        Expression expression = expression();
        consume(TokenType.SEMICOLON, "Se esperaba ';' al final de asignacion.");
        return new AssignmentStatement(name.getLexeme(), expression);
    }

    private Statement printStatement() {
        consume(TokenType.LPAREN, "Se esperaba '(' en print.");
        Expression expression = expression();
        consume(TokenType.RPAREN, "Se esperaba ')' en print.");
        consume(TokenType.SEMICOLON, "Se esperaba ';' al final de print.");
        return new PrintStatement(expression);
    }

    private Expression expression() {
        Expression expression = term();
        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            Expression right = term();
            expression = new BinaryExpression(operator.getLexeme(), expression, right);
        }
        return expression;
    }

    private Expression term() {
        Expression expression = factor();
        while (match(TokenType.MULTIPLY, TokenType.DIVIDE)) {
            Token operator = previous();
            Expression right = factor();
            expression = new BinaryExpression(operator.getLexeme(), expression, right);
        }
        return expression;
    }

    private Expression factor() {
        if (match(TokenType.NUMBER)) {
            return new NumberExpression(Integer.parseInt(previous().getLexeme()));
        }
        if (match(TokenType.IDENTIFIER)) {
            return new VariableExpression(previous().getLexeme());
        }
        if (match(TokenType.LPAREN)) {
            Expression inner = expression();
            consume(TokenType.RPAREN, "Se esperaba ')' despues de expresion.");
            return inner;
        }
        throw error(peek(), "Se esperaba numero, identificador o expresion entre parentesis.");
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) {
            return false;
        }
        return peek().getType() == type;
    }

    private Token consume(TokenType type, String message) {
        if (check(type)) {
            return advance();
        }
        throw error(peek(), message);
    }

    private Token advance() {
        if (!isAtEnd()) {
            current++;
        }
        return previous();
    }

    private boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private ParserException error(Token token, String message) {
        return new ParserException("[Sintactico " + token.getLine() + ":" + token.getColumn() + "] " + message);
    }
}
