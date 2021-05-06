package com.jadehelena.transactions.util.creators

import com.jadehelena.transactions.domain.Transaction

class TransactionCreator {

    static Transaction createPersistedTransaction(){
        new Transaction(1, -20.00, AccountCreator.createPersistedAccount())
    }

}
