
package bank.generators;

import dto.AccountStatementDTO;
import dto.GoalDTO;
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
    public void generator(AccountStatementDTO accountStatement) {
        try {
            // Create a simple data structure for JSON serialization
            Map<String, Object> statement = new HashMap<>();
            statement.put("title", "BANK STATEMENT");
            statement.put("accountName", accountStatement.accountName());
            statement.put("accountType", accountStatement.accountType());
            statement.put("balance", accountStatement.balance());
            statement.put("transactionHistory", accountStatement.transactionHistory());

            // Convert goals to simple strings to avoid circular references
            List<String> goalStrings =
                    accountStatement.goals().stream()
                    .map(GoalDTO::toString)
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