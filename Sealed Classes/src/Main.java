public class Main {
    public static void main(String[] args) {

        Notification n1 = new Email("email@gmail", "Hi", "Welcome!");
        Notification n2 = new SMS("Hello", "1234");
        Notification n3 = new WhatsApp("Hello!", "1234");

        n1.sendNotification();
        n2.sendNotification();
        n3.sendNotification();
    }
}