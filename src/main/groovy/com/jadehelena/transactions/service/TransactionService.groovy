package com.jadehelena.transactions.service


import com.jadehelena.transactions.domain.Transaction
import com.jadehelena.transactions.enums.OperationTypeEnum
import com.jadehelena.transactions.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

import javax.transaction.Transactional

@Service
class TransactionService {

    @Autowired
    TransactionRepository transactionRepository

    @Autowired
    AccountService accountService

    @Transactional
    Transaction save(Transaction transaction) {
        validatesTransactionParams(transaction)

        transactionRepository.save(transaction)
    }

    def validatesTransactionParams(Transaction transaction) {
        def operationTypeEnum = checkIfEnumExist(transaction)
        setNegativeAmountAccordingToEnum(operationTypeEnum, transaction)
        accountService.findByIdOrThrowBadRequestException(transaction.account.getId())
    }

    def checkIfEnumExist(Transaction transaction) {
        def operationTypeEnum = OperationTypeEnum.findEnumByType(transaction.getOperationType())
        if(operationTypeEnum == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid operationType")
        }

        operationTypeEnum
    }

    def setNegativeAmountAccordingToEnum(operationType, transaction) {
        if(operationType in OperationTypeEnum.negativeAmountEnums()) {
            transaction.setAmount(transaction.amount * -1)
        }
    }
}
