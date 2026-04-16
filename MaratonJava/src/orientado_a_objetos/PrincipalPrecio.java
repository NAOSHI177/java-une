package orientado_a_objetos;

public class PrincipalPrecio {

	public static void main(String[] args) {
		Precio precio = new Precio();
		precio.valorCostos = 140;
		
		Producto produto = new Producto();
		produto.definirPrecio(precio, 20, 15);
		
		System.out.println("Valor costos: " + precio.valorCostos);
		System.out.println("Valor impuestos: " + precio.valorImpuestos);
		System.out.println("Valor margen: " + precio.valorMargen);
		System.out.println("Precio venda: " + precio.precioVenta);
	}
	
}
