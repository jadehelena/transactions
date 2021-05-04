package com.jadehelena.transactions.service

import com.jadehelena.transactions.domain.Account
import com.jadehelena.transactions.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import javax.transaction.Transactional

@Service
class AccountService {
    @Autowired
    AccountRepository accountRepository

    Account findByIdOrThrowBadRequestException(long id) {
        accountRepository.findById(id).orElseThrow({
            new ResponseStatusException(HttpStatus.NOT_FOUND, "account not found")
        })
    }
}
