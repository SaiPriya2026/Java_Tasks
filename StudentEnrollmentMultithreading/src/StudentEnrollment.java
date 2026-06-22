import java.util.concurrent.*;

public class StudentEnrollment {


    static void saveEnrollment(String name, String course) {
        String threadname = Thread.currentThread().getName();
        System.out.println(threadname + " Saving enrollment for " + name + " in " + course);
    }

    static void sendWelcomeEmail(String name) {
        String threadname = Thread.currentThread().getName();
        System.out.println(threadname+  " Sending welcome email to " + name);
    }

    static void generateInvoice(String name) {
        String threadname = Thread.currentThread().getName();
        System.out.println(threadname+" Generating invoice for " + name);
    }

    static void sendWhatsApp(String name) {
        String threadname = Thread.currentThread().getName();
        System.out.println(threadname+ " Sending WhatsApp message to " + name);
    }

    static void updateCRM(String name) {
        String threadname = Thread.currentThread().getName();
        System.out.println(threadname +" Updating CRM for " + name);
    }
}