public class Payment {

    private Integer studentId;
    private Double amount;
    private String status;
    private String month;

    public Payment(Integer studentId, Double amount, String status, String month) {
        this.studentId = studentId;
        this.amount = amount;
        this.status = status;
        this.month = month;
    }

    public Double getAmount() { return amount; }
    public String getStatus() { return status; }
    public String getMonth() { return month; }
}