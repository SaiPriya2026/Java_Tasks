public record WhatsApp(String message, String phno) implements Notification {

    @Override
    public void sendNotification() {
        System.out.println("Sending WhatsApp msg " + message);
    }
}
