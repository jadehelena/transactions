package com.jadehelena.transactions.service


import com.jadehelena.transactions.domain.Transaction
import com.jadehelena.transactions.enums.OperationTypeEnum
import com.jadehelena.transactions.repository.TransactionRepository
import com.jadehelena.transactions.util.creators.TransactionCreator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException

@ExtendWith(SpringExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService

    @Mock
    private TransactionRepository transactionRepository

    @Mock
    private AccountService accountService

    @Spy
    private TransactionService transactionServiceMock

    @DisplayName("Should return negative amount when debit operation")
    @Test
    void shouldReturnNegativeAmount_WhenDebitOperation() {
        Transaction transaction = TransactionCreator.createMockedTransactionWithOperationTypeAsWithdraw()
        transactionService.setNegativeAmountIfDebitOperation(OperationTypeEnum.WITHDRAW, transaction)

        Assertions.assertEquals(transaction.getAmount(), -35.00.doubleValue())
    }

    @DisplayName("Should return positive amount when credit operation")
    @Test
    void shouldReturnPositiveAmount_WhenCreditOperation() {
        Transaction transaction = TransactionCreator.createMockedTransactionWithOperationTypeAsPayment()
        transactionService.setNegativeAmountIfDebitOperation(OperationTypeEnum.PAYMENT, transaction)

        Assertions.assertEquals(transaction.getAmount(), 100.00.doubleValue())
    }

    @DisplayName("Should save transaction when valid operationType")
    @Test
    void shouldSaveTransaction_WhenValidOperationType() {
        Transaction transaction = TransactionCreator.createMockedTransactionWithOperationTypeAsPayment()

        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class)))
                .thenReturn(transaction)

        Transaction persistedTransaction = transactionService.save(transaction)

        Assertions.assertTrue(persistedTransaction instanceof Transaction)
    }

    @DisplayName("Should return bad request when invalid operation type")
    @Test
    void shouldReturnBadRequest_WhenInvalidOperationType() {
        Transaction transaction = TransactionCreator.createMockedTransactionWithInvalidOperationType()

        Mockito.doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid operationType"))
                .when(transactionServiceMock).validatesTransactionParams(Mockito.any())

        Assertions.assertThrows(ResponseStatusException.class, { ->
            transactionService.save(transaction)
        })
    }

    @DisplayName("Should return bad request when doesnt exists available credit limit for transaction")
    @Test
    void shouldReturnBadRequest_WhenDoesntExistsAvailableCreditLimitForTransaction() {
        Transaction transaction = TransactionCreator.createMockedInsufficentAmountPersistedTransaction()

        Mockito.doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "This account doesnt have enough available credit limit"))
                .when(transactionServiceMock).validatesTransactionParams(Mockito.any(Transaction.class))

        Assertions.assertThrows(ResponseStatusException.class, { ->
            transactionServiceMock.save(transaction)
        })
    }
}
