package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Método para ler todas (repassando o trabalho do repository)
    public java.util.List<Account> listAll() {
        return accountRepository.findAll();
    }

    // Método para buscar por ID
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    // A Lógica do Depósito
    public Account deposit(Long id, BigDecimal amount) {
        // 1. Verificar se o valor é positivo
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor do depósito deve ser maior que zero!");
        }

        // 2. Buscar a conta (Se não achar, lança erro)
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada!"));

        // 3. Somar o saldo (Matemática segura com BigDecimal)
        account.setBalance(account.getBalance().add(amount));

        // 4. Salvar a atualização
        return accountRepository.save(account);
    }
}