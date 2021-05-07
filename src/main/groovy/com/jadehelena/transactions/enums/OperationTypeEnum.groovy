package com.jadehelena.transactions.enums

import com.jadehelena.transactions.domain.Transaction
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

enum OperationTypeEnum {
    IN_CASH_PURCHASE(1),
    WITH_INSTALLMENTS_PURCHASE(2),
    WITHDRAW(3),
    PAYMENT(4)

    public int type

    OperationTypeEnum(int type) {
        this.type = type
    }

    static def debitOperations(){
        [IN_CASH_PURCHASE, WITH_INSTALLMENTS_PURCHASE, WITHDRAW]
    }

    static def creditOperations(){
        [PAYMENT]
    }

    static OperationTypeEnum findByType(int type) {
        values().find { operationTypeEnum -> operationTypeEnum.type == type }
    }

    static OperationTypeEnum getOperationTypeEnumIfExists(Transaction transaction) {
        OperationTypeEnum operationTypeEnum = findByType(transaction.getOperationType())
        if(operationTypeEnum == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid operationType")
        }

        operationTypeEnum
    }
}
