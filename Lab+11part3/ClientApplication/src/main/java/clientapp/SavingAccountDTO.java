package clientapp;

import java.util.ArrayList;
import java.util.List;

public class SavingAccountDTO {
    private int accountNumber;
    private String fullName;
    private String email;
    private List<Transaction> transactionList;

    public SavingAccountDTO() {
    }

    public SavingAccountDTO(int accountNumber, String fullName, String email) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.email = email;
        this.transactionList = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }

    public double getBalance() {
        if (!transactionList.isEmpty())
            return transactionList.stream().map(Transaction::getAmount)
                    .reduce(Double::sum)
                    .get();
        return 0.0;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SavingAccountDTO{" +
                "accountNumber=" + accountNumber +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", transactionList=" + transactionList +
                '}';
    }
}
