import java.util.*;
import java.util.stream.*;
import java.util.Comparator;
public class Main {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student(101, "Raju", "Java", 15000, true),
                new Student(102, "Rani", "Python", 0, false),
                new Student(103, "Kiran", "Java", 10000, true),
                new Student(104, "Anil", "DevOps", 0, false),
                new Student(105, "Suresh", "Python", 20000, true),
                new Student(106, "Mahesh", "Java", 0, true)
        );


        System.out.println("Active Students:");
        students.stream()
                .filter(s -> s.active)
                .forEach(System.out::println);

        System.out.println("----------------------------------");
        System.out.println("Paid Students:");
        students.stream()
                .filter(s -> s.feePaid > 0)
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        System.out.println("Java Students:");
        students.stream()
                .filter(s -> s.course.equalsIgnoreCase("Java"))
                .forEach(System.out::println);

        System.out.println("---------------------------------");
        System.out.println("Student Names:");
        students.stream()
                .map(s -> s.name)
                .forEach(System.out::println);

        System.out.println("--------------------------------");
        double totalFee = students.stream()
                .mapToDouble(s -> s.feePaid)
                .sum();
        System.out.println("Total Fee: " + totalFee);


        System.out.println("-------------------------------");
        long unpaidCount = students.stream()
                .filter(s -> s.feePaid == 0)
                .count();
        System.out.println("Unpaid Students: " + unpaidCount);

        System.out.println("-------------------------------");
        System.out.println("Students Grouped by Course:");
        Map<String, List<Student>> grouped =
                students.stream()
                        .collect(Collectors.groupingBy(s -> s.course));

        grouped.forEach((course, list) -> {
            System.out.println(course + " -> " + list);
        });


        System.out.println("-------------------------------");
        System.out.println("Highest Fee Paid Student:");
        students.stream()
                .sorted(Comparator.comparing((Student s) -> s.feePaid).reversed())
                .limit(1)
                .forEach((System.out::println));

        System.out.println("------------------------------");
        System.out.println("Top 3 Fee Students:");
        students.stream()
                .sorted(Comparator.comparing((Student s) -> s.feePaid).reversed())
                .limit(3)
                .forEach(System.out::println);
    }
}