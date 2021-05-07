package com.jadehelena.transactions.util.creators

import com.jadehelena.transactions.domain.Transaction

class TransactionCreator {

    static Transaction createMockedPersistedTransaction() {
        new Transaction(1, -20.00, AccountCreator.createMockedPersistedAccount())
    }

    static Transaction createMockedTransactionWithOperationTypeAsWithdraw() {
        new Transaction(3, 35.00, AccountCreator.createMockedPersistedAccount())
    }

    static Transaction createMockedTransactionWithOperationTypeAsPayment() {
        new Transaction(4, 100.00, AccountCreator.createMockedPersistedAccount())
    }

    static Transaction createMockedTransactionWithInvalidOperationType() {
        new Transaction(999, 1.00, AccountCreator.createMockedPersistedAccount())
    }
}
