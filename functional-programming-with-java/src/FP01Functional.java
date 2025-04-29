import java.util.List;

public class FP01Functional {
    public static void main(String[] args) {
        List<Integer> list = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        printAllNumbersInFunctional(list);
        printEvenNumbersInFunctional(list);
        printSquaresOfEvenNumbersInFunctional(list);

    }
        private static void print(int number){
        System.out.println(number);
    }

    private static boolean isEven(int number){
        return number % 2 == 0;
    }
    private static void printAllNumbersInFunctional(List<Integer> integers) {
        integers.forEach(System.out::println);
    }

    private static void printEvenNumbersInFunctional(List<Integer> integers) {
        integers.stream()
//                .filter(FP01Functional::isEven)
                .filter(e -> e % 2 != 0)
                .forEach(System.out::println);
    }
    private static void printSquaresOfEvenNumbersInFunctional(List<Integer> integers) {
        integers.stream()
                .filter(e -> e % 2 != 0)
                .map(e -> e * e *e)
                .forEach(System.out::println);
    }

}
