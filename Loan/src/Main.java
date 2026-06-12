import java.util.*;

public class Main {
    public static void main(String[] args) {

        Customer c = new Customer();
        c.setCustomerName("Kiran");
        c.setAge(25);
        c.setMonthlySalary(60000.0);
        c.setCreditScore(750);
        c.setExistingEmi(20000.0);
        c.setEmploymentType("SALARIED");
        c.setRequestedLoanAmount(900000.0);

        List<String> failedReasons = new ArrayList<>();

        LoanRule ageRule = cust -> {
            boolean valid = cust.getAge() >= 21 && cust.getAge() <= 60;
            if (!valid) failedReasons.add("Age not between 21 and 60");
            return valid;
        };

        LoanRule salaryRule = cust -> {
            boolean valid = cust.getMonthlySalary() >= 50000;
            if (!valid) failedReasons.add("Salary is below 50000");
            return valid;
        };

        LoanRule creditRule = cust -> {
            boolean valid = cust.getCreditScore() >= 750;
            if (!valid) failedReasons.add("Credit score is below 750");
            return valid;
        };

        LoanRule emiRule = cust -> {
            boolean valid = cust.getExistingEmi() < (cust.getMonthlySalary() * 0.4);
            if (!valid) failedReasons.add("Existing EMI is more than allowed limit");
            return valid;
        };

        LoanRule loanAmountRule = cust -> {
            boolean valid = cust.getRequestedLoanAmount() <= (cust.getMonthlySalary() * 20);
            if (!valid) failedReasons.add("Loan amount exceeds allowed limit");
            return valid;
        };

        LoanRule employmentRule = cust -> {
            boolean valid = cust.getEmploymentType().equalsIgnoreCase("SALARIED")
                    || cust.getEmploymentType().equalsIgnoreCase("BUSINESS");
            if (!valid) failedReasons.add("Invalid employment type");
            return valid;
        };

        LoanRule finalRule = ageRule
                .and(salaryRule)
                .and(creditRule)
                .and(emiRule)
                .and(loanAmountRule)
                .and(employmentRule);

        boolean isEligible = finalRule.validate(c);

        System.out.println("Customer: " + c.getCustomerName());

        if (isEligible) {
            System.out.println("Loan Status: APPROVED");
            System.out.println("Reason: All eligibility conditions satisfied");
        } else {
            System.out.println("Loan Status: REJECTED");
            System.out.println("Failed Rules:");
            for (String reason : failedReasons) {
                System.out.println("- " + reason);
            }
        }
    }
}