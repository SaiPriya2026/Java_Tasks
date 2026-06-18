import java.util.Arrays;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Integer> s = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int r =s.stream()
                .mapToInt(i -> i )
                .sum();

        System.out.println(r);





    }
}