package com.jadehelena.transactions.enums

enum OperationTypeEnum {
    IN_CASH_PURCHASE(1),
    WITH_INSTALLMENTS_PURCHASE(2),
    WITHDRAW(3),
    PAYMENT(4)

    public int value

    OperationTypeEnum(int value) {
        this.value = value
    }

    static List<OperationTypeEnum> allEnums(){
        Arrays.asList(OperationTypeEnum.values())
    }

    static def negativeAmountEnums(){
        [IN_CASH_PURCHASE, WITH_INSTALLMENTS_PURCHASE, WITHDRAW]
    }

    static OperationTypeEnum findByType(int operationType) {
        values().find { operationTypeEnum -> operationTypeEnum.value == operationType }
    }
}
