package com.jadehelena.transactions.repository

import com.jadehelena.transactions.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository  extends JpaRepository<Account, Long> {

}
