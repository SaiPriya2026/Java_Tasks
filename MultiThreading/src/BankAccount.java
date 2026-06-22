import java.util.*;


public class BankAccount {

    private String accNumber;

    public BankAccount(String accNumber) {
        this.accNumber = accNumber;
    }

    public List<String> getTransactions() {
        return Arrays.asList("TX1", "TX2", "TX3");
    }


    public void generateStatement() {

        List<String> txs = getTransactions();

        for (String tx : txs) {
            String threadName = Thread.currentThread().getName();
            System.out.println(accNumber + " -> " + tx + " -> "+threadName);
        }
    }
}