import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        Student s = new Student();
        s.setStudentId(1);
        s.setStudentName("Suresh");
        s.setSkills(Arrays.asList("Java"));
        s.setExperience(2);
        s.setBudget(25000.0);

        List<Course> courses = Arrays.asList(
                new Course(1, "Java Fullstack", "Java", "Intermediate", 25000.0, "6 months", 4.8),
                new Course(2, "Spring Boot Microservices", "Java", "Advanced", 18000.0, "4 months", 4.7),
                new Course(3, "Python Fullstack", "Python", "Intermediate", 22000.0, "6 months", 4.6),
                new Course(4, "Data Science", "Python", "Advanced", 30000.0, "8 months", 4.9)
        );


        Predicate<Course> budgetFilter = c -> s.getBudget() >= c.getFee();

        Function<Student, List<Course>> recommendCourses = student -> {
            List<Course> result = new ArrayList<>();

            for (Course c : courses) {

                boolean skillMatch =
                        (student.getSkills().contains("Java") && c.getTechnology().equalsIgnoreCase("Java")) ||
                                (student.getSkills().contains("Python") && c.getTechnology().equalsIgnoreCase("Python"));

                boolean levelMatch =
                        (student.getExperience() == 0 && c.getLevel().equalsIgnoreCase("Beginner")) ||
                                (student.getExperience() >= 3 && c.getLevel().equalsIgnoreCase("Advanced")) ||
                                (student.getExperience() > 0 && student.getExperience() < 3);

                if (skillMatch && levelMatch && budgetFilter.test(c)) {
                    result.add(c);
                }
            }

            return result;
        };


        Comparator<Course> sortByRating =
                (c1, c2) -> Double.compare(c2.getRating(), c1.getRating());

        Consumer<Course> printCourse = c -> {
            System.out.println(c.getCourseName() + c.getFee() + " - Rating: " + c.getRating());
        };


        List<Course> recommended = recommendCourses.apply(s);

        recommended.sort(sortByRating);

        System.out.println("Recommended Courses for " + s.getStudentName() + ":");
        for (Course c : recommended) {
            printCourse.accept(c);
        }
    }
}