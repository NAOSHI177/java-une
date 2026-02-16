import java.util.Scanner;

class scanner_test{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println(nombre);
        sc.close();
    }
}