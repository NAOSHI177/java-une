package com.compilator.ui;

import com.compilator.service.CompileResult;
import com.compilator.service.CompilerService;
import java.io.Serializable;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CompilerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sourceCode = "int a = 10;\nint b = 20;\na = a + b * 2;\nprint(a);";
    private String tokens;
    private String ast;
    private String intermediateCode;
    private String message;

    private final CompilerService compilerService = new CompilerService();

    public void compile() {
        CompileResult result = compilerService.compile(sourceCode);
        this.message = result.getMessage();
        this.tokens = result.getTokens();
        this.ast = result.getAst();
        this.intermediateCode = result.getIntermediateCode();
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
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
