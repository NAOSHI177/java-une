package builder.model.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import builder.model.Cliente;
import builder.model.ItemPedido;
import builder.model.PedidoVenta;
import builder.model.PedidoVentaBuilder;

public class PedidoVentaTest {

	@Test
	public void calcularValorTotalPedidoParaClienteVip() {
		PedidoVenta pedidoVenta = new PedidoVenta();
		
		Cliente cliente = new Cliente();
		cliente.setNombre("Juan");
		cliente.setVip(true);
		pedidoVenta.setCliente(cliente);
		
		ItemPedido item1 = new ItemPedido();
		item1.setNombre("Calculadora");
		item1.setValorUnitario(new BigDecimal("200"));
		item1.setCantidad(2);
		
		ItemPedido item2 = new ItemPedido();
		item2.setNombre("Mochila");
		item2.setValorUnitario(new BigDecimal("200"));
		item2.setCantidad(1);
		
		List<ItemPedido> itensPedido = Arrays.asList(item1, item2);
		pedidoVenta.setItensPedido(itensPedido);
		
		BigDecimal valorTotal = pedidoVenta.getValorTotal();
		
		assertEquals(new BigDecimal("576").doubleValue(), valorTotal.doubleValue(), 0.0001);
	}
	
	@Test
	public void calcularValorTotalPedidoParaClienteVipComBuilder() {
		PedidoVenta pedidoVenta = new PedidoVentaBuilder()
										.conClienteVip("Juan  Silva")
										.conItem("Calculadora", 2, "200")
										.conItem("Mochila", 1, "200")
										.conNumero("0")
										.construir();
										//construirValidando();
		
		BigDecimal valorTotal = pedidoVenta.getValorTotal();
		
		assertEquals(new BigDecimal("576").doubleValue(), valorTotal.doubleValue(), 0.0001);
	}
	
}
