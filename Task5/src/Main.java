import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        Student s1 = new Student();
        s1.setStudentName("Ravi");
        s1.setCourseName("Java Fullstack");
        s1.setGraduationYear(2023);
        s1.setPercentage(75.0);
        s1.setBacklogs(0);
        s1.setMockRating(4.8);
        s1.setSkills(Arrays.asList("Java"));
        s1.setResumeAvailable(true);
        s1.setFeePaid(true);

        Student s2 = new Student();
        s2.setStudentName("Sneha");
        s2.setCourseName("Python Fullstack");
        s2.setGraduationYear(2022);
        s2.setPercentage(70.0);
        s2.setBacklogs(0);
        s2.setMockRating(4.6);
        s2.setSkills(Arrays.asList("Python"));
        s2.setResumeAvailable(true);
        s2.setFeePaid(true);

        Student s3 = new Student();
        s3.setStudentName("Kiran");
        s3.setCourseName("Java Fullstack");
        s3.setGraduationYear(2021);
        s3.setPercentage(55.0);
        s3.setBacklogs(2);
        s3.setMockRating(3.5);
        s3.setSkills(Arrays.asList("Java"));
        s3.setResumeAvailable(false);
        s3.setFeePaid(false);

        students.add(s1);
        students.add(s2);
        students.add(s3);

        List<Student> eligible = new ArrayList<>();
        Map<String, String> rejected = new HashMap<>();


        Predicate<Student> eligibility = s ->
                s.getGraduationYear() >= 2022 &&
                        s.getPercentage() >= 60 &&
                        s.getBacklogs() == 0 &&
                        s.getMockRating() >= 4 &&
                        s.getResumeAvailable() &&
                        s.getFeePaid() &&
                        (s.getSkills().contains("Java") || s.getSkills().contains("Python"));

        Function<Student, String> rejectionReason = s -> {
            List<String> reasons = new ArrayList<>();

            if (s.getGraduationYear() < 2022) reasons.add("Old graduation year");
            if (s.getPercentage() < 60) reasons.add("Low percentage");
            if (s.getBacklogs() > 0) reasons.add("Backlogs available");
            if (s.getMockRating() < 4) reasons.add("Low mock rating");
            if (!s.getResumeAvailable()) reasons.add("Resume missing");
            if (!s.getFeePaid()) reasons.add("Fee not paid");

            return String.join(", ", reasons);
        };

        Consumer<Student> printStudent = s ->
                System.out.println(s.getStudentName() + " - " +
                        s.getCourseName() + " - Rating: " + s.getMockRating());

        for (Student s : students) {
            if (eligibility.test(s)) {
                eligible.add(s);
            } else {
                rejected.put(s.getStudentName(), rejectionReason.apply(s));
            }
        }


        Comparator<Student> sortByRating =
                (a, b) -> Double.compare(b.getMockRating(), a.getMockRating());

        eligible.sort(sortByRating);


        Map<String, List<Student>> grouped =
                eligible.stream().collect(Collectors.groupingBy(Student::getCourseName));

        System.out.println("Eligible Students for Placement:");
        int i = 1;
        for (Student s : eligible) {
            System.out.print(i++ + ". ");
            printStudent.accept(s);
        }

        System.out.println("\nRejected Students:");
        for (Map.Entry<String, String> entry : rejected.entrySet()) {
            System.out.println(entry.getKey() + " - Reason: " + entry.getValue());
        }

        System.out.println("\nGrouped by Course:");
        for (String course : grouped.keySet()) {
            System.out.println(course + ": " + grouped.get(course).size() + " students");
        }
    }
}