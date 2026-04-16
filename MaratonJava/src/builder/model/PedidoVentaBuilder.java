package builder.model;


import java.math.BigDecimal;
import java.util.ArrayList;



public class PedidoVentaBuilder {

	private PedidoVenta instancia;
	
	public PedidoVentaBuilder() {
		this.instancia = new PedidoVenta();
	}
	
	public PedidoVentaBuilder conClienteVip(String nombre) {
		definirCliente(nombre, true);
		return this;
	}
	
	public PedidoVentaBuilder conClienteNormal(String nombre) {
		definirCliente(nombre, false);
		return this;
	}
	
	private void definirCliente(String nombre, boolean vip) {
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setVip(true);
		this.instancia.setCliente(cliente);
    }
	
	public PedidoVentaBuilder conItem(String nombre, Integer cantidad, String valorUnitario) {
		ItemPedido item = new ItemPedido();
		item.setNombre(nombre);
		item.setValorUnitario(new BigDecimal(valorUnitario));
		item.setCantidad(cantidad);
		
		if (this.instancia.getItensPedido() == null) {
			this.instancia.setItensPedido(new ArrayList<ItemPedido>());
		}
		
		this.instancia.getItensPedido().add(item);
		
		return this;
	}
	
	
	  public PedidoVentaBuilderValido conNumero(String numero) {
	  this.instancia.setNumero(numero); return new
	  PedidoVentaBuilderValido(instancia); }
	 
}