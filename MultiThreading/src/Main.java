import java.util.*;
import java.util.concurrent.*;
public class Main {
    public static void main(String[] args) {

        List<BankAccount> accounts = Arrays.asList(
                new BankAccount("ACC1"),
                new BankAccount("ACC2"),
                new BankAccount("ACC3")
        );

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (BankAccount acc : accounts) {
            executor.submit(() -> acc.generateStatement());
        }

        executor.shutdown();
    }
}
