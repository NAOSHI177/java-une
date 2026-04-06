package conexiones;

import java.util.Scanner;

public class CodeProfe {
    	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int edad;
		System.out.print("Ingrese su edad ");
		edad = entrada.nextInt();

		String nombre;
		System.out.println("Ingrese su nombre : ");
		nombre = entrada.next();

		
		 boolean puedeConducir = edad >= 18;
		 
		 
		 if (!puedeConducir) {
		 System.out.println("Usted es menor de edad. "
		 		+ "Ingrese el nombre de su padre");
		 
		  nombre = entrada.next(); }
		 
		 
		 if (puedeConducir){ 
            System.out.println("Puede continuar " + "conduciendo "+ nombre); 
        }
		  
		  //bloque boolean bloque = false; 
         /*  if(bloque){ int edadBloque = 20;
		  System.out.println("La edad es: "+ edad);
		  System.out.println("La edad en el bloque es: "+ edadBloque); 
        }    
		  System.out.println("La edad fuera del bloque es: ");
		  */
		 entrada.close();
    } 
}
