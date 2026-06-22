public class Notifications {


    static void whatsappNotification(String name)
    {
        String thread = Thread.currentThread().getName();
        System.out.println(thread+" WhatsApp number"+" -> "+" Dear "+name+" Big Billion Day Sale is live now. Get up to 80% discount on mobiles, laptops, fashion, and electronics. Hurry up!");
    }
     static void emailNotification(String name)
    {
        String thread = Thread.currentThread().getName();
        System.out.println(thread+" Email"+" -> "+" Dear "+name+" Big Billion Day Sale is live now. Get up to 80% discount on mobiles, laptops, fashion, and electronics. Hurry up!");
    }

}

