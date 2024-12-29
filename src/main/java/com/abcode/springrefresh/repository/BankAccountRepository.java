package com.abcode.springrefresh.repository;

import com.abcode.springrefresh.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}