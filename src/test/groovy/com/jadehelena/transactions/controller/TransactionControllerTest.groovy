package com.jadehelena.transactions.controller

import com.jadehelena.transactions.service.AccountService
import com.jadehelena.transactions.service.TransactionService
import com.jadehelena.transactions.util.creators.AccountCreator
import com.jadehelena.transactions.util.creators.TransactionCreator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService

    @MockBean
    private AccountService accountService

    @Autowired
    private MockMvc mockMvc

    @Test
    @DisplayName("Should return success when post transaction")
    void shouldReturnSuccess_WhenPostTransaction() {
        Mockito.when(transactionService.save(Mockito.any()))
                .thenReturn(TransactionCreator.createPersistedTransaction())

        Mockito.when(accountService.findByIdOrThrowBadRequestException(1L))
                .thenReturn(AccountCreator.createPersistedAccount())

        mockMvc.perform( MockMvcRequestBuilders
                .post("/transactions")
                .content("{\"operationType\": 1, \"accountId\": 1, \"amount\": 20.0}")
                .header("Content-Type", "application/json"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"amount\": -20.0}"))
                .andExpect(content().json("{\"operationType\": 1}"))
    }

}
