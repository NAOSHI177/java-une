package Alumnos;

public class AlumnoSeccionMain {
	public static void main(String[] args) {
        Seccion seccionB = new Seccion();
        seccionB.codigo = "Maternal B";
        seccionB.nombreProfesor = "Tia Maria";
        seccionB.alumnos = new Alumno[3];

        seccionB.alumnos[0] = new Alumno();
        seccionB.alumnos[0].nombre = "Juan";
        seccionB.alumnos[0].edad = 3;

        Alumno alumno1 = new Alumno();
        alumno1.nombre = "Laura";
        alumno1.edad = 4;

        seccionB.alumnos[1] = alumno1;

        seccionB.imprimirListaDeAlumnos();

//        
    }
}