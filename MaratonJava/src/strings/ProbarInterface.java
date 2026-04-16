package strings;

public class ProbarInterface {
    public static void main(String[] args) {
        Imprimible c = new Cobrar(100);
        c.imprimir();

        Enviable e = (Enviable) c;
        e.enviar(" xxxx | factura numero");
    }

}
