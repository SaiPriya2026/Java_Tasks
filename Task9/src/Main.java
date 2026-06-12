import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {


        List<InterviewFeedback> list = new ArrayList<>();

        InterviewFeedback s1 = new InterviewFeedback();
        s1.setStudentName("Ravi");
        s1.setTechnicalRating(8.0);
        s1.setCommunicationRating(7.0);
        s1.setCodingRating(7.5);
        s1.setConfidenceRating(8.0);
        s1.setProblemSolvingRating(8.5);
        s1.setWeaknesses(Arrays.asList("Communication"));

        InterviewFeedback s2 = new InterviewFeedback();
        s2.setStudentName("Kiran");
        s2.setTechnicalRating(5.0);
        s2.setCommunicationRating(4.0);
        s2.setCodingRating(5.5);
        s2.setConfidenceRating(5.0);
        s2.setProblemSolvingRating(5.0);
        s2.setWeaknesses(Arrays.asList("Coding", "Confidence"));

        list.add(s1);
        list.add(s2);

        Function<InterviewFeedback, Double> overall = s ->
                (s.getTechnicalRating() +
                        s.getCommunicationRating() +
                        s.getCodingRating() +
                        s.getConfidenceRating() +
                        s.getProblemSolvingRating()) / 5;


        Function<Double, String> performance = r -> {
            if (r >= 8) return "Excellent";
            if (r >= 6) return "Good";
            if (r >= 4) return "Average";
            return "Needs Improvement";
        };

        Predicate<InterviewFeedback> eligible =
                s -> overall.apply(s) >= 6 && s.getCodingRating() >= 6;


        Consumer<InterviewFeedback> print = s -> {
            double rating = overall.apply(s);

            System.out.println("\nStudent: " + s.getStudentName());
            System.out.println("Overall Rating: " + rating);
            System.out.println("Performance: " + performance.apply(rating));
            System.out.println("Placement Eligible: " + (eligible.test(s) ? "Yes" : "No"));

            if (!eligible.test(s)) {
                System.out.println("Suggestions: Improve " + String.join(", ", s.getWeaknesses()));
            }
        };

        list.sort((a, b) -> Double.compare(overall.apply(b), overall.apply(a)));

        Map<String, List<InterviewFeedback>> grouped =
                list.stream().collect(Collectors.groupingBy(
                        s -> performance.apply(overall.apply(s))
                ));


        list.forEach(print);


        System.out.println("\nGrouped by Performance:");
        grouped.forEach((k, v) ->
                System.out.println(k + " -> " + v.size() + " students"));
    }
}