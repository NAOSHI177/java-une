package colecciones.banco.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import colecciones.banco.modelo.Producto;

public class RegistrProductos {
 
  public static void main(String[] args) {
    Collection productos = new ArrayList<>();
     
    System.out.println("##### Registro de productos #####\n");
     
    try (Scanner entrada = new Scanner(System.in)) {
      String continuar = "s";
      while ("s".equalsIgnoreCase(continuar)) {
        System.out.print("SKU: ");
        String sku = entrada.nextLine();
 
        System.out.print("Nombre: ");
        String nombre = entrada.nextLine();
         
        Producto producto = new Producto(sku, nombre);
        if (productos.contains(producto)) {
          System.err.println("Este producto ya  existe. Utilizé otro SKU!");
        } else {
          productos.add(producto);
          System.out.println("Producto agregado.");
        }
         
        System.out.print("Desea agregar mas  productos? (s/n) ");
         
        continuar = entrada.nextLine();
      }
    }
     
    productos.forEach(System.out::println);
 
    System.out.println("Fin");
  }
 
}