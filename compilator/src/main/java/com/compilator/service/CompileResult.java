package com.compilator.service;

public class CompileResult {
    private final boolean success;
    private final String tokens;
    private final String ast;
    private final String intermediateCode;
    private final String message;

    public CompileResult(boolean success, String tokens, String ast, String intermediateCode, String message) {
        this.success = success;
        this.tokens = tokens;
        this.ast = ast;
        this.intermediateCode = intermediateCode;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTokens() {
        return tokens;
    }

    public String getAst() {
        return ast;
    }

    public String getIntermediateCode() {
        return intermediateCode;
    }

    public String getMessage() {
        return message;
    }
}
