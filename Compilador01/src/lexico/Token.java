package lexico;



public class Token {
	private String tipo;
	private String valor;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Token(String tipo, String valor) {
		super();
		this.tipo = tipo;
		this.valor = valor;
	}

}
