package strings;


public class Factura implements Imprimible, Enviable {

	private int numero;
	
	public Factura(int numero) {
		this.numero = numero;
	}
	
	@Override
	public void imprimir() {
		//implementa aqui
		System.out.println("Vamos a imprimir esta factura de numero: " + numero);
	}
	
	public void agregarPedido(String producto) {
		
	}

	@Override
	public void enviar(String email) {
		System.out.println("Enviando la factura de número: " + numero + " al correo: " + email);
		
	}

}