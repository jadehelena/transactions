package com.jadehelena.transactions.service

import com.jadehelena.transactions.domain.Account
import com.jadehelena.transactions.repository.AccountRepository
import com.jadehelena.transactions.util.creators.AccountCreator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.server.ResponseStatusException

@ExtendWith(SpringExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService

    @Mock
    private AccountRepository accountRepository

    @DisplayName("Should return bad request when account doesnt exists")
    @Test
    void shouldReturnBadRequest_WhenAccountDoesntExists() {
        Mockito.when(accountRepository.findById(2L))
                .thenReturn(Optional.empty())

        Assertions.assertThrows(ResponseStatusException.class, { ->
            accountService.findByIdOrThrowBadRequestException(2L)
        })
    }

    @DisplayName("Should return account when it exists")
    @Test
    void shouldReturnAccount_WhenItExists() {
        Account account = AccountCreator.createMockedPersistedAccount()

        Mockito.when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account))

        Account accountFoundById = accountService.findByIdOrThrowBadRequestException(1L)

        Assertions.assertTrue(accountFoundById instanceof Account)
    }

    @DisplayName("Should save account when documentNumber has 11 digits")
    @Test
    void shouldSaveAccount_WhenDocumentNumberHas11Digits() {
        Account account = AccountCreator.createMockedPersistedAccount()

        Mockito.when(accountRepository.save(Mockito.any(Account.class)))
                .thenReturn(account)

        Account persistedAccount = accountService.save(account)

        Assertions.assertTrue(persistedAccount instanceof Account)
    }
}
