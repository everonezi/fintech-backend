package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    // Injetando os dois Repositories
    public UserService(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public User createUser(User user) {
        // 1. Salva o usuário primeiro (pra ter o ID dele)
        User savedUser = userRepository.save(user);

        // 2. Service criando a conta bancária automaticamente 
        Account account = new Account();
        account.setUser(savedUser); // Liga a conta ao usuário
        account.setBalance(BigDecimal.ZERO); // Começa zerada
        account.setAgency("0001"); // Agência padrão
        // Gera um número aleatório para a conta (usando UUID só pra facilitar)
        account.setNumber(UUID.randomUUID().toString().substring(0, 8));

        // 3. Salva a conta no banco
        accountRepository.save(account);

        return savedUser;
    }
}