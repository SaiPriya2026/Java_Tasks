import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student(1, "Ravi", "Java Fullstack"),
                new Student(2, "Kiran", "Python Fullstack"),
                new Student(3, "Sneha", "Java Fullstack")
        );


        List<Course> courses = Arrays.asList(
                new Course("Java Fullstack", 30000.0, "Raj"),
                new Course("Python Fullstack", 25000.0, "Anil"),
                new Course("DevOps", 20000.0, "Raj"),
                new Course("Data Science", 35000.0, "Meena")
        );


        List<Enrollment> enrollments = Arrays.asList(
                new Enrollment(1, "Java Fullstack"),
                new Enrollment(2, "Python Fullstack"),
                new Enrollment(3, "Java Fullstack")
        );


        List<Payment> payments = Arrays.asList(
                new Payment(1, 30000.0, "SUCCESS", "JUNE"),
                new Payment(2, 25000.0, "SUCCESS", "JUNE"),
                new Payment(3, 20000.0, "PENDING", "JUNE")
        );


        double totalRevenue = payments.stream()
                .filter(p -> p.getStatus().equals("SUCCESS"))
                .collect(Collectors.summingDouble(Payment::getAmount));


        Map<String, Long> courseReport = enrollments.stream()
                .collect(Collectors.groupingBy(
                        Enrollment::getCourseName,
                        Collectors.counting()
                ));


        double pendingAmount = payments.stream()
                .filter(p -> p.getStatus().equals("PENDING"))
                .collect(Collectors.summingDouble(Payment::getAmount));


        Map<String, List<String>> trainerReport = courses.stream()
                .collect(Collectors.groupingBy(
                        Course::getTrainerName,
                        Collectors.mapping(Course::getCourseName, Collectors.toList())
                ));

        Map<String, Long> monthlyAdmissions = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getMonth,
                        Collectors.counting()
                ));


        List<Course> topCourses = courses.stream()
                .sorted((a, b) -> Double.compare(b.getFee(), a.getFee()))
                .limit(5)
                .collect(Collectors.toList());



        System.out.println("Course Wise Enrollment Report:");
        courseReport.forEach((k, v) ->
                System.out.println(k + " : " + v + " Students"));

        System.out.println("\nTotal Revenue: ₹" + totalRevenue);
        System.out.println("Pending Payments: ₹" + pendingAmount);

        System.out.println("\nTrainer Wise Courses:");
        trainerReport.forEach((k, v) ->
                System.out.println(k + " -> " + v));

        System.out.println("\nMonthly Admissions:");
        monthlyAdmissions.forEach((k, v) ->
                System.out.println(k + " : " + v + " Students"));

        System.out.println("\nTop High Fee Courses:");
        topCourses.forEach(c ->
                System.out.println(c.getCourseName() + " - ₹" + c.getFee()));
    }
}