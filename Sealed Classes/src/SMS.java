public record SMS(String message, String phno)  implements Notification {

    @Override
    public void sendNotification() {
        System.out.println("Sending SMS to " + phno);

    }
}