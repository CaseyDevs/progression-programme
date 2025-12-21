package bank.generators;

import bank.BankAccount;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;

public class JSONStatementGenerator implements StatementGenerator {
    private final ObjectMapper objectMapper;

    public JSONStatementGenerator() { this.objectMapper = new ObjectMapper(); }

    @Override
    public void generator(BankAccount account) {
        try {
            ObjectWriter write = objectMapper.writerWithDefaultPrettyPrinter();

            objectMapper.writeValue(new File("statement.json"), account);

        } catch (Exception e) {
            System.err.println("Error generating JSON statement: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
