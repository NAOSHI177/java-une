package orientado_a_objetos;


public class Paciente {

	double peso;
	double altura;
	//
	IMC calcularIndiceDeMassaCorporal() {
		IMC imc = new IMC();
		double indice = peso / (altura * altura);
		imc.indice = indice;
		
		if (indice < 18.5) {
			imc.porDebajoIdeal = true;
		} else if (indice < 25) {
			imc.pesoIdeal = true;
		} else {
			imc.obesidad = true;
			
			if (indice < 30) {
				imc.gradoObesidad = "Sobre peso";  
			} else if (indice < 35) {
				imc.gradoObesidad = "I";
			} else if (indice < 40) {
				imc.gradoObesidad = "II";
			} else {
				imc.gradoObesidad = "III";
			}
		}
		
		return imc;
	}
	
}
