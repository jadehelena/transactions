package com.jadehelena.transactions.service

import com.jadehelena.transactions.domain.Account
import com.jadehelena.transactions.domain.Transaction
import com.jadehelena.transactions.enums.OperationTypeEnum
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
            new ResponseStatusException(HttpStatus.NOT_FOUND, "account id not found")
        })
    }

    @Transactional
    Account save(Account account){
        accountRepository.save(account)
    }

    def updateAvailableCreditLimit(Account account, Double amount) {
        account.setAvailableCreditLimit(account.getAvailableCreditLimit() + amount)
        save(account)
    }

    def checkIfAvailableCreditLimitExist(Transaction transaction, OperationTypeEnum operationTypeEnum) {
        if(transaction.getAccount().getAvailableCreditLimit() < transaction.getAmount() && operationTypeEnum in OperationTypeEnum.debitOperations()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This account doesnt have enough available credit limit")
        }
    }
}
