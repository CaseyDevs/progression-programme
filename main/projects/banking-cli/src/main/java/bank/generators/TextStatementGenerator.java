package bank.generators;

import dto.AccountStatementDTO;
import dto.GoalDTO;

import java.io.FileWriter;
import java.io.IOException;

public class TextStatementGenerator implements StatementGenerator {
    public TextStatementGenerator() {}

    @Override
    public void generator(AccountStatementDTO accountStatement) {
        try {
            FileWriter fileWriter = new FileWriter("statement.txt");
            fileWriter.write(generateStatementText(accountStatement));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateStatementText(AccountStatementDTO accountStatement) {
        StringBuilder statement = new StringBuilder();
        statement.append("### BANK STATEMENT ###\n");
        statement.append("\nAccount Name: ").append(accountStatement.accountName());
        statement.append("\nAccount Type: ").append(accountStatement.accountType());
        statement.append("\nOwner: ").append(accountStatement.ownerName());
        statement.append("\nBalance: $").append(accountStatement.balance());
        statement.append("\nTransaction History: \n").append(String.join("\n", accountStatement.transactionHistory()));
        statement.append("\nGoals: ");

        if (accountStatement.goals().isEmpty()) {
            statement.append("None");
        } else {
            for (GoalDTO goal : accountStatement.goals()) {
                statement.append("\n- Name: ").append(goal.name());
                statement.append("\n- Target: ").append(goal.target());
                statement.append("\n- Start Date: ").append(goal.startDate());
            }
        }

        statement.append("\nInterest Rate: ").append(accountStatement.interestRateDescription());

        return statement.toString();
    }
}