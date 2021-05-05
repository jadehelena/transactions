package com.jadehelena.transactions.domain

import org.hibernate.annotations.CreationTimestamp

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id

    @NotNull
    private int operationType

    @NotNull
    private Double amount

    @NotNull
    @ManyToOne
    private Account account

    @CreationTimestamp
    private LocalDateTime eventDate

    Transaction() {
    }

    Transaction(Long id, int operationType, Double amount, Account account) {
        this.id = id
        this.operationType = operationType
        this.amount = amount
        this.account = account
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    int getOperationType() {
        return operationType
    }

    void setOperationType(int operationType) {
        this.operationType = operationType
    }

    Account getAccount() {
        return account
    }

    void setAccount(Account account) {
        this.account = account
    }

    Double getAmount() {
        return amount
    }

    void setAmount(Double amount) {
        this.amount = amount
    }

    LocalDateTime getEventDate() {
        return eventDate
    }

    void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate
    }
}
