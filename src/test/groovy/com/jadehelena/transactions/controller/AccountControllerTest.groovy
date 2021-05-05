package com.jadehelena.transactions.controller

import com.jadehelena.transactions.domain.Account
import com.jadehelena.transactions.service.AccountService
import com.jadehelena.transactions.util.creators.AccountCreator
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.server.ResponseStatusException

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @MockBean
    private AccountService accountService

    @Autowired
    private MockMvc mockMvc

    @Test
    @DisplayName("Should return success when get existing account")
    void shouldReturnSuccess_WhenGetExistingAccount() {
        Mockito.when(this.accountService.findByIdOrThrowBadRequestException(1L))
                .thenReturn(AccountCreator.createPersistedAccount())

        mockMvc.perform(
                get("/accounts/{id}", 1L)
                .header("Content-Type", "application/json"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'document':'02193246573'}"))
    }

    @Test
    @DisplayName("Should return not found when get non existing account")
    void shouldReturnSuccess_WhenGetNonExistingAccount() {
        Mockito.when(this.accountService.findByIdOrThrowBadRequestException(2L))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found"))

        mockMvc.perform(
                get("/accounts/{id}", 2L)
                .header("Content-Type", "application/json"))
                .andExpect(status().isNotFound())
    }

    @Test
    @DisplayName("Should return success when post account")
    void shouldReturnSuccess_WhenPostAccount() {
        Mockito.when(this.accountService.save(Mockito.any(Account.class)))
                .thenReturn(AccountCreator.createPersistedAccount())

        mockMvc.perform( MockMvcRequestBuilders
                .post("/accounts")
                .content("{\"document\": \"02193246573\"}")
                .header("Content-Type", "application/json"))
                .andExpect(status().isCreated())
    }

    @Test
    @DisplayName("Should return bad request when post account blank document")
    void shouldReturnBadRequest_WhenPostAccountBlankDocument() {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/accounts")
                .content("{\"document\": \"\"}")
                .header("Content-Type", "application/json"))
                .andExpect(status().isBadRequest())
    }
}
