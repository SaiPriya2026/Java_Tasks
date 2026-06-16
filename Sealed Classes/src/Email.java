public record Email(String to, String subject, String body)
        implements Notification  {

    @Override
    public void sendNotification() {
        System.out.println("sending email "+ to );
    }
}
