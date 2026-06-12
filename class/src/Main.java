@FunctionalInterface
interface MyTask {
    void run();
}

public class Main {
    public static void main(String[] args) {

        // Lambda implementation
        MyTask task = () -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
            }
        };

        // Create thread using lambda
        Thread t = new Thread(() -> task.run());

        t.start();
    }
}