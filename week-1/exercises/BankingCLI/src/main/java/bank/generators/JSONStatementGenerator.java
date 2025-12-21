
package bank.generators;

import bank.BankAccount;
import bank.Goal;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JSONStatementGenerator implements StatementGenerator {
    private final ObjectMapper objectMapper;

    public JSONStatementGenerator() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void generator(BankAccount account) {
        try {
            // Create a simple data structure for JSON serialization
            Map<String, Object> statement = new HashMap<>();
            statement.put("title", "BANK STATEMENT");
            statement.put("accountName", account.getAccountDisplayName());
            statement.put("accountType", account.getAccountType());
            statement.put("balance", account.getBalance());
            statement.put("transactionHistory", account.getTransactionHistoryAsString());

            // Convert goals to simple strings to avoid circular references
            List<String> goalStrings =
                    account.getUser().getGoals().stream()
                    .map(Goal::toString)
                    .collect(Collectors.toList());

            statement.put("goals", goalStrings);

            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(new File("statement.json"), statement);

        } catch (Exception e) {
            System.err.println("Error generating JSON statement: " + e.getMessage());
            e.printStackTrace();
        }
    }
}