package com.jadehelena.transactions.controller

import com.jadehelena.transactions.domain.Account
import com.jadehelena.transactions.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping('accounts')
class AccountController {

    @Autowired
    AccountService accountService

    @GetMapping('{id}')
    ResponseEntity<Account> findOne(@PathVariable long id) {
        ResponseEntity.ok(accountService.findByIdOrThrowBadRequestException(id))
    }
}
