package builder.model;



public class PedidoVentaBuilderValido {

	private PedidoVenta instancia;
	
	public PedidoVentaBuilderValido(PedidoVenta instancia) {
	    this.instancia = instancia;
    }

	public PedidoVenta construir() {
		return this.instancia;
		
	}
	public PedidoVenta construirValidando() {
		if (this.instancia.getNumero()=="0") {
			throw new IllegalStateException("ERROR  numero");
		}
			
		
		return this.instancia;
		
	}
}