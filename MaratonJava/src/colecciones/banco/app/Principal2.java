package colecciones.banco.app;

import java.util.ArrayList;
import java.util.List;

import colecciones.banco.modelo.Persona;

public class Principal2 {

	public static void main(String[] args) {
		Persona persona1 = new Persona("Juan Perez", "12312312311");
		Persona persona2 = new Persona("Maria Gonzalez", "22222222211");

       List<Persona> personas = new ArrayList<>();
		//Set<Persona> personas = new HashSet<>();
		personas.add(persona1);
		personas.add(persona2);
         System.out.println("List de personas---toStrin");
        System.out.println(personas.toString());

       Persona persona3 = personas.get(1);
//        System.out.println(persona3);
       
		System.out.println("for personas");
		for (int i = 0; i < personas.size(); i++) {
			System.out.println(personas.get(i).getNombre());
		}
		/// iterar
		System.out.println("iterar personas");
//        for (Persona persona : personas) {

//            System.out.println(persona.getNombre());
//        }

		Persona persona4 = new Persona("Juan Gonzalez", "123123123113");

		boolean existe = personas.contains(persona1);
		System.out.println(existe);

		System.out.println(persona1.equals(persona4));
	}

}