package orientado_a_objetos;

public class Producto {

	void definirPrecio(Precio precio, double porcentajelImpuestos,
			double margemGanancia) {
		precio.valorImpuestos = precio.valorCostos 
				* (porcentajelImpuestos / 100);
		precio.valorMargen = precio.valorCostos * (margemGanancia / 100);
		precio.precioVenta = precio.valorCostos 
				+ precio.valorImpuestos + precio.valorMargen;
	}
	
}
