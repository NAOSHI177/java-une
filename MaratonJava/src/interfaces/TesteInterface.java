package interfaces;

public class TesteInterface {

	public static void main(String[] args) {

		Imprimible i = new Factura(1234);
		i.imprimir();

		Enviable e = (Enviable) i;
		e.enviar("xxx@ggg.com");

		Factura f = (Factura) e;
		f.agregarPedido("pizza");

	}
}