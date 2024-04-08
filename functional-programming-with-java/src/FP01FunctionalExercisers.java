import java.util.List;

public class FP01FunctionalExercisers {
    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        printCoursesEx1(courses);
        printCoursesEx2(courses);
        printCoursesEx3(courses);
        printCoursesNumberOfCharactersEx4(courses);
    }
    private static void printCoursesEx1(List<String> list){
        list.forEach(System.out::println);
    }
    private static void printCoursesEx2(List<String> list){
        list.stream()
                .filter(e -> e.contains("Spring"))
                .forEach(System.out::println);
    }
    private static void printCoursesEx3(List<String> list){
        list.stream()
                .filter(e -> e.length() <= 4)
                .forEach(System.out::println);
    }

    private static void printCoursesNumberOfCharactersEx4(List<String> list){
        list.stream()
                .map(String::length)
                .forEach(System.out::println);
    }

}
