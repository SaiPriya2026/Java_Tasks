import java.util.function.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee();

        emp.setEmployeeId(1);
        emp.setEmployeeName("Ravi");
        emp.setDepartment("IT");
        emp.setRole("Developer");
        emp.setExperience(6);
        emp.setSalary(60000.0);
        emp.setPerformanceRating(4.6);

        double oldSalary = emp.getSalary();

        Predicate<Employee> Rating = e ->e.getPerformanceRating() >= 4.5;

        Predicate<Employee> Experience = e ->e.getExperience() >= 5;

        Consumer<Employee> Print = e -> {
            System.out.println("Employee: " + e.getEmployeeName());
            System.out.println("Role: " + e.getRole());
        };

        Function<Employee, Double> Hike = e -> {
            if(e.getPerformanceRating() < 3) {
                return 0.0;
            }
            if(Rating.test(e)) {
                return 20.0;
            }
            if(Experience.test(e)) {
                return 15.0;
            }
            if(e.getDepartment().equalsIgnoreCase("Developer")) {
                return 10.0;
            }
            if(e.getDepartment().equalsIgnoreCase("Tester")) {
                return 8.0;
            }
            return 0.0;


            };

        SalaryProcessor processor = e -> {
            double hike = Hike.apply(e);
            return e.getSalary() + (e.getSalary() * hike/100);

        };

        double hikePercent = Hike.apply(emp);
        double newSalary = processor.process(emp);


        Print.accept(emp);
        System.out.println("Old Salary: " + oldSalary);
        System.out.println("Hike Applied: " + hikePercent +"%");
        System.out.println("Final Salary: "+ newSalary);


    }
}