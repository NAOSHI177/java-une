package fundamentos;

public class Seccion {

    String codigo;
    String nombreProfesor;
    Alumno[] alumnos;

    void imprimirListaDeAlumnos() {
        for (Alumno alumno : alumnos) {
            if (alumno != null) {
                System.out.printf("%s (%d años)%n",
                		alumno.nombre, alumno.edad);
            } else {
                System.out.println("fin lista de alumnos");
            }
        }
    }

}