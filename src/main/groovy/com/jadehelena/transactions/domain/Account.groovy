package com.jadehelena.transactions.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    @NotNull @NotEmpty
    private String document

    Account(Long id, String document) {
        this.id = id
        this.document = document
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    String getDocument() {
        return document
    }

    void setDocument(String document) {
        this.document = document
    }
}
