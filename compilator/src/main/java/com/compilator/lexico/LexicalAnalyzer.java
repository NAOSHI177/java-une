package com.compilator.lexico;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int current = 0;
    private int line = 1;
    private int column = 1;

    public LexicalAnalyzer(String source) {
        this.source = source == null ? "" : source;
    }

    public List<Token> scanTokens() {
        while (!isAtEnd()) {
            scanToken();
        }
        tokens.add(new Token(TokenType.EOF, "", line, column));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case ' ':
            case '\r':
            case '\t':
                break;
            case '\n':
                line++;
                column = 1;
                break;
            case '=':
                addToken(TokenType.ASSIGN, "=");
                break;
            case '+':
                addToken(TokenType.PLUS, "+");
                break;
            case '-':
                addToken(TokenType.MINUS, "-");
                break;
            case '*':
                addToken(TokenType.MULTIPLY, "*");
                break;
            case '/':
                addToken(TokenType.DIVIDE, "/");
                break;
            case '(':
                addToken(TokenType.LPAREN, "(");
                break;
            case ')':
                addToken(TokenType.RPAREN, ")");
                break;
            case ';':
                addToken(TokenType.SEMICOLON, ";");
                break;
            default:
                if (isDigit(c)) {
                    number(c);
                } else if (isAlpha(c)) {
                    identifier(c);
                } else {
                    throw new LexicalException(errorPrefix() + "Caracter no reconocido: '" + c + "'");
                }
        }
    }

    private void number(char first) {
        StringBuilder lexeme = new StringBuilder().append(first);
        while (!isAtEnd() && isDigit(peek())) {
            lexeme.append(advance());
        }
        addToken(TokenType.NUMBER, lexeme.toString());
    }

    private void identifier(char first) {
        StringBuilder lexeme = new StringBuilder().append(first);
        while (!isAtEnd() && isAlphaNumeric(peek())) {
            lexeme.append(advance());
        }
        String text = lexeme.toString();
        if ("int".equals(text)) {
            addToken(TokenType.KEYWORD_INT, text);
        } else if ("print".equals(text)) {
            addToken(TokenType.KEYWORD_PRINT, text);
        } else {
            addToken(TokenType.IDENTIFIER, text);
        }
    }

    private char advance() {
        char c = source.charAt(current++);
        column++;
        return c;
    }

    private char peek() {
        return source.charAt(current);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private void addToken(TokenType type, String lexeme) {
        tokens.add(new Token(type, lexeme, line, Math.max(1, column - lexeme.length())));
    }

    private String errorPrefix() {
        return "[Lexico " + line + ":" + Math.max(1, column - 1) + "] ";
    }
}
