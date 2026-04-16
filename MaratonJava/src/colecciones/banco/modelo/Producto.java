package colecciones.banco.modelo;
public class Producto {
 
  private String sku;
  private String nombre;
 
  public Producto(String sku, String nombre) {
    this.sku = sku;
    this.nombre = nombre;
  }
 
  // getters e setters
 
  @Override
  public String toString() {
    return "Producto [sku=" + sku + ", nombre=" + nombre + "]";
 
    
  }
  
  //equals
  public boolean equals(Object obj) {
	  Producto otro = (Producto) obj;
	  return this.sku.equals(otro.getSku());
	}

public String getSku() {
	return sku;
}

public void setSku(String sku) {
	this.sku = sku;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
 
}