package com.jadehelena.transactions.domain

import org.hibernate.validator.constraints.Length

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id

    @NotNull @NotEmpty @Size(min=11, max=11, message="document must have 11 digits")
    private String documentNumber

    @OneToMany(mappedBy = "account")
    List<Transaction> getTransactions() { }

    Account(Long id, String documentNumber) {
        this.id = id
        this.documentNumber = documentNumber
    }

    Account() {
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getDocumentNumber() {
        return documentNumber
    }

    void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber
    }

}
