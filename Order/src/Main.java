import java.util.*;

public class Main {
    public static void main(String[] args) {

        Order order = new Order();
        order.setOrderId("ORD101");
        order.setCustomerName("Ravi");
        order.setItems(Arrays.asList("Laptop", "Mouse"));
        order.setTotalAmount(5000.0);
        order.setCouponCode("GENAI10");

        double originalAmount = order.getTotalAmount();

        OrderProcessor validateOrder = o -> {
            if (o.getItems() == null || o.getItems().isEmpty()) {
                throw new RuntimeException("Invalid Order");
            }
            return o;
        };

        OrderProcessor applyCoupon = o -> {
            if ("GENAI10".equals(o.getCouponCode())) {
                o.setTotalAmount(o.getTotalAmount() * 0.9);
            }
            return o;
        };


        OrderProcessor calculateGst = o -> {
            o.setTotalAmount(o.getTotalAmount() * 1.18);
            return o;
        };


        OrderProcessor confirmPayment = o -> {
            o.setPaymentStatus("SUCCESS");
            return o;
        };

        OrderProcessor assignDelivery = o -> {
            o.setDeliveryStatus("ASSIGNED");
            return o;
        };

        OrderProcessor updateDelivery = o -> {
            o.setDeliveryStatus("OUT FOR DELIVERY");
            return o;
        };

        Order finalOrder = validateOrder
                .andThen(applyCoupon)
                .andThen(calculateGst)
                .andThen(confirmPayment)
                .andThen(assignDelivery)
                .andThen(updateDelivery)
                .process(order);


        System.out.println("Order ID: " + finalOrder.getOrderId());
        System.out.println("Original Amount: " + originalAmount);
        System.out.println("Coupon Applied: " + finalOrder.getCouponCode());
        System.out.println("GST Added: 18%");
        System.out.println("Final Amount: " + finalOrder.getTotalAmount());
        System.out.println("Payment Status: " + finalOrder.getPaymentStatus());
        System.out.println("Delivery Status: " + finalOrder.getDeliveryStatus());
    }
}