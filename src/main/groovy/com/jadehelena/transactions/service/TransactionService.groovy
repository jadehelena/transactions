package com.jadehelena.transactions.service


import com.jadehelena.transactions.domain.Transaction
import com.jadehelena.transactions.enums.OperationTypeEnum
import com.jadehelena.transactions.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.transaction.Transactional

@Service
class TransactionService {

    @Autowired
    TransactionRepository transactionRepository

    @Transactional
    Transaction save(Transaction transaction) {
        validatesTransactionParams(transaction)

        transactionRepository.save(transaction)
    }

    def validatesTransactionParams(transaction) {
        OperationTypeEnum operationTypeEnum = OperationTypeEnum.getOperationTypeEnumIfExists(transaction)
        setNegativeAmountIfDebitOperation(operationTypeEnum, transaction)
    }

    def setNegativeAmountIfDebitOperation(operationTypeEnum, transaction) {
        if(operationTypeEnum in OperationTypeEnum.debitOperations()) {
            transaction.setAmount(transaction.amount * -1)
        }
    }
}
