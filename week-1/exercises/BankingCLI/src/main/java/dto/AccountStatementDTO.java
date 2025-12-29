package dto;

import java.util.List;

public class AccountStatementDTO {
    private String accountName;
    private String accountType;
    private double balance;
    private List<String> transactionHistory;
    private List<GoalDTO> goals;
    private String ownerName;
    private String interestRateDescription;

    public AccountStatementDTO() {}

    public AccountStatementDTO(String accountName, String accountType, double balance,
                              List<String> transactionHistory, List<GoalDTO> goals, 
                              String ownerName, String interestRateDescription) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.transactionHistory = transactionHistory;
        this.goals = goals;
        this.ownerName = ownerName;
        this.interestRateDescription = interestRateDescription;
    }

    // Getters and Setters
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public List<GoalDTO> getGoals() {
        return goals;
    }

    public void setGoals(List<GoalDTO> goals) {
        this.goals = goals;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getInterestRateDescription() {
        return interestRateDescription;
    }

    public void setInterestRateDescription(String interestRateDescription) {
        this.interestRateDescription = interestRateDescription;
    }

    @Override
    public String toString() {
        return "AccountStatementDTO{" +
                "accountName='" + accountName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", transactionHistory=" + transactionHistory +
                ", goals=" + goals +
                ", ownerName='" + ownerName + '\'' +
                ", interestRateDescription='" + interestRateDescription + '\'' +
                '}';
    }
}
