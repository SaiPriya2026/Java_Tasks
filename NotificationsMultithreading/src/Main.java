import java.util.*;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<String> customers = Arrays.asList("Priya","Santosh","john","Sam","june");
        ExecutorService executor = Executors.newFixedThreadPool(5);


        for(String customer:customers){
            executor.submit(() -> Notifications.whatsappNotification(customer));
            executor.submit(() -> Notifications.emailNotification(customer));
        }
        executor.shutdown();
    }
}