import java.util.List;

public class FP01Structured {
    public static void main(String[] args) {
        List<Integer> integers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        printAllNumbersInStructured(integers);
        printEvenNumbersInStructured(integers);
    }

    private static void printAllNumbersInStructured(List<Integer> integers) {
        for (int numbers : integers){
            System.out.println(numbers);
        }

    }
    private static void printEvenNumbersInStructured(List<Integer> integers) {
        for (int number : integers){
            if (number % 2 == 0){
                System.out.println(number);
            }
        }

    }
}
