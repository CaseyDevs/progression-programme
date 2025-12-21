package bank.generators;

import bank.BankAccount;
import java.io.FileWriter;
import java.io.IOException;

public class TextStatementGenerator implements StatementGenerator {
    public TextStatementGenerator() {}

    @Override
    public void generator(BankAccount account) {
        try {
            FileWriter fileWriter = new FileWriter("statement.txt");
            fileWriter.write(account.generateStatement());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
