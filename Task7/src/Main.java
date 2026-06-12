import java.util.*;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {

        PaymentRequest req = new PaymentRequest();
        req.setCustomerName("Ravi");
        req.setAmount(25000.0);
        req.setPaymentMode("UPI");
        req.setCouponCode("COURSE10");

        Predicate<PaymentRequest> validate =
                r -> r.getAmount() > 0;

        Function<PaymentRequest, PaymentRequest> applyCoupon = r -> {
            if ("COURSE10".equalsIgnoreCase(r.getCouponCode())) {
                r.setAmount(r.getAmount() * 0.9); // 10% discount
            }
            return r;
        };


        Function<PaymentRequest, PaymentRequest> addCharges = r -> {
            r.setAmount(r.getAmount() * 1.02);
            return r;
        };


        Supplier<String> txnId =
                () -> "TXN" + System.currentTimeMillis();


        PaymentGateway upi = r -> {
            PaymentResponse res = new PaymentResponse();
            res.setTransactionId(txnId.get());
            res.setPaymentStatus("SUCCESS");
            res.setFinalAmount(r.getAmount());
            res.setMessage("UPI Payment Success");
            return res;
        };

        PaymentGateway card = r -> {
            PaymentResponse res = new PaymentResponse();
            res.setTransactionId(txnId.get());
            res.setPaymentStatus("SUCCESS");
            res.setFinalAmount(r.getAmount());
            res.setMessage("Card Payment Success");
            return res;
        };


        if (validate.test(req)) {

            double original = req.getAmount();
            req = applyCoupon.andThen(addCharges).apply(req);
            PaymentGateway gateway = req.getPaymentMode().equals("UPI") ? upi : card;
            PaymentResponse res = gateway.pay(req);

            System.out.println("Payment Mode: " + req.getPaymentMode());
            System.out.println("Original Amount: " + original);
            System.out.println("Coupon Applied: " + req.getCouponCode());
            System.out.println("Final Amount: " + res.getFinalAmount());
            System.out.println("Transaction ID: " + res.getTransactionId());
            System.out.println("Payment Status: " + res.getPaymentStatus());
        }
    }
}