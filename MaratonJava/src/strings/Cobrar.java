package strings;


public class Cobrar implements Enviable, Imprimible {

    int numero;

    

    public Cobrar(int numero) {
        this.numero = numero;
    }

    @Override
    public void imprimir() {
                System.out.println("Vamos a imprimir esta factura: " + numero);

    }

    @Override
    public void enviar(String email) {
            System.out.println(email+ "-" + numero);
    }




}
