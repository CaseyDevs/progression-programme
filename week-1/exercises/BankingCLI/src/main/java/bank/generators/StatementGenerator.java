package bank.generators;

import dto.AccountStatementDTO;

public interface StatementGenerator {

    void generator(AccountStatementDTO accountStatementDTO);
}
