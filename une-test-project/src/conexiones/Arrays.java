package conexiones;

public class Arrays {
    public static void main(String[] args) {
        int[] numbers = new int[5];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        for(int number: numbers){
            System.out.println(numbers[number] + "-");
        }
    }
}
