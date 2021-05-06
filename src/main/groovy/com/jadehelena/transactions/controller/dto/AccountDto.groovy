package com.jadehelena.transactions.controller.dto

import com.jadehelena.transactions.domain.Account

class AccountDto {
    private Long id
    private String documentNumber

    AccountDto(Account account){
        this.id = account.getId()
        this.documentNumber = account.getDocumentNumber()
    }

    Long getId() {
        return id
    }

    String getDocumentNumber() {
        return documentNumber
    }
}
