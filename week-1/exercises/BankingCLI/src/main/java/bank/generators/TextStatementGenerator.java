package bank.generators;

import bank.BankAccount;
import java.io.FileWriter;
import java.io.IOException;

public class TextStatementGenerator {
    BankAccount account;

    public TextStatementGenerator(BankAccount account) {
        this.account = account;
    }

    public void generate() throws IOException {
        try {
            FileWriter fileWriter = new FileWriter("statement.txt");
            fileWriter.write(account.generateStatement());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
