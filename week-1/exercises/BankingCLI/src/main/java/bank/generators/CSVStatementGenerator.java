package bank.generators;

import bank.BankAccount;

import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVStatementGenerator implements StatementGenerator {
    @Override
    public void generator(BankAccount account) {
        try {
            FileWriter fileWriter = new FileWriter("statement.csv");
            
            // Write CSV header
            String[] headers = {"Field", "Value"};
            fileWriter.write(convertToCSV(headers) + "\n");
            
            // Write account information
            String[] titleRow = {"Title", "BANK STATEMENT"};
            fileWriter.write(convertToCSV(titleRow) + "\n");
            
            String[] accountNameRow = {"Account Name", account.getAccountDisplayName()};
            fileWriter.write(convertToCSV(accountNameRow) + "\n");
            
            String[] accountTypeRow = {"Account Type", account.getAccountType()};
            fileWriter.write(convertToCSV(accountTypeRow) + "\n");
            
            String[] balanceRow = {"Balance", String.valueOf(account.getBalance())};
            fileWriter.write(convertToCSV(balanceRow) + "\n");
            
            String[] transactionRow = {"Transaction History", account.getTransactionHistoryAsString()};
            fileWriter.write(convertToCSV(transactionRow) + "\n");
            
            // Write goals if available
            if (account.getUser() != null && account.getUser().getGoals() != null) {
                String goalsString = account.getUser().getGoals().stream()
                    .map(goal -> goal.toString())
                    .collect(Collectors.joining("; "));
                String[] goalsRow = {"Goals", goalsString};
                fileWriter.write(convertToCSV(goalsRow) + "\n");
            }
            
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        String escapedData = data.replaceAll("\\R", " ");
        if (escapedData.contains(",") || escapedData.contains("\"") || escapedData.contains("'")) {
            escapedData = escapedData.replace("\"", "\"\"");
            escapedData = "\"" + escapedData + "\"";
        }
        return escapedData;
    }
}
