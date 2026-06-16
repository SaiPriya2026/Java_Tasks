public sealed interface Notification permits Email, SMS, WhatsApp{

    public  void sendNotification();

}
