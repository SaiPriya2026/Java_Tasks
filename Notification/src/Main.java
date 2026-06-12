import java.util.*;

public class Main {
    public static void main(String[] args) {

        Notification n = new Notification();
        n.setUserName("Ravi");
        n.setEmail("ravi@gmail.com");
        n.setMobile("9876543210");
        n.setMessage("Your class starts at 7:30 AM");
        n.setPriority("HIGH");
        n.setNotificationType("EMAIL");


        NotificationSender emailSender = x -> {
            if (x.getEmail() != null)
                System.out.println("Email Sent to " + x.getEmail());
        };

        NotificationSender smsSender = x -> {
            if (x.getMobile() != null)
                System.out.println("SMS Sent to " + x.getMobile());
        };

        NotificationSender whatsappSender = x -> {
            if (x.getMobile() != null)
                System.out.println("WhatsApp Sent to " + x.getMobile());
        };

        NotificationSender pushSender = x -> {
            System.out.println("Push Notification Sent");
        };


        Map<String, NotificationSender> senders = new HashMap<>();
        senders.put("EMAIL", emailSender);
        senders.put("SMS", smsSender);
        senders.put("WHATSAPP", whatsappSender);
        senders.put("PUSH", pushSender);

        System.out.println("Sending " + n.getPriority() + " priority notification...");

        if (n.getPriority().equalsIgnoreCase("HIGH")) {
            emailSender.send(n);
            whatsappSender.send(n);
        } else {
            NotificationSender sender = senders.get(n.getNotificationType());
            if (sender != null) {
                sender.send(n);
            }
        }

        System.out.println("Message: " + n.getMessage());
    }
}