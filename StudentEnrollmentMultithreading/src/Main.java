import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        String studentName = "Sai Priya";
        String course = "Java";


        executor.submit(() -> StudentEnrollment.saveEnrollment(studentName, course));
        executor.submit(() -> StudentEnrollment.sendWelcomeEmail(studentName));
        executor.submit(() -> StudentEnrollment.generateInvoice(studentName));
        executor.submit(() -> StudentEnrollment.sendWhatsApp(studentName));
        executor.submit(() -> StudentEnrollment.updateCRM(studentName));

        executor.shutdown();
    }
}