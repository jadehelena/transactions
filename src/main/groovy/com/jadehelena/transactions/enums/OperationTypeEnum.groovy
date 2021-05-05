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

    static def allEnums(){
        Arrays.asList(OperationTypeEnum.values())
    }

    static def negativeAmountEnums(){
        [IN_CASH_PURCHASE, WITH_INSTALLMENTS_PURCHASE, WITHDRAW]
    }

    static def findEnumByType(int type) {
        switch (type) {
            case 1:
                return IN_CASH_PURCHASE
            case 2:
                return WITH_INSTALLMENTS_PURCHASE
            case 3:
                return WITHDRAW
            case 4:
                return PAYMENT
            default:
                return null
        }
    }
}
