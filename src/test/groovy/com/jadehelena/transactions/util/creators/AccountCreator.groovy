package com.jadehelena.transactions.util.creators

import com.jadehelena.transactions.domain.Account

class AccountCreator {

    static Account createPersistedAccount(){
        new Account(1L, "02193246573")
    }

}
