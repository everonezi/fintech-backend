package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts") // Define que a URL base é /accounts
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Listar todas as contas (só pra gente testar fácil)
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Buscar conta específica pelo ID (ex: /accounts/1)
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        return accountRepository.findById(id)
                .map(account -> ResponseEntity.ok(account)) // Se achar, retorna 200 OK com a conta
                .orElse(ResponseEntity.notFound().build()); // Se não achar, retorna 404 Not Found
    }
}