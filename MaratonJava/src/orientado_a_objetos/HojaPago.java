package orientado_a_objetos;

public class HojaPago {

	// double calcularSalario(int, int, double, double)
	double calcularSalario(int horasNormal, int horasExtras, double valorHoraNormal, double valorHoraExtra) {

		double valorHorasNormales = horasNormal * valorHoraNormal;
		double valorHorasExtras = horasExtras * valorHoraExtra;

		return valorHorasNormales + valorHorasExtras;

	}

}
