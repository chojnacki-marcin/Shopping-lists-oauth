package com.chojnacki.shoppinglist.service;

import com.chojnacki.shoppinglist.repository.AccountRepository;
import com.chojnacki.shoppinglist.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class JpaSecurityService implements SecurityService{

    private final JpaShoppingListService shoppingListService;
    private final AccountRepository accountRepository;

    @Autowired
    public JpaSecurityService(JpaShoppingListService shoppingListService, AccountRepository accountRepository) {
        this.shoppingListService = shoppingListService;
        this.accountRepository = accountRepository;
    }

    @Override
    public boolean isOwner(Authentication authentication, long shoppingListId){
        String email = authentication.getName();
        Account account = accountRepository.findByEmail(email);
        return shoppingListService.isOwner(account, shoppingListId);
    }
}