package com.plannedwallet.accounts.service;

import com.plannedwallet.accounts.models.Account;
import com.plannedwallet.accounts.models.UserAccounts;
import com.plannedwallet.accounts.repositories.AccountsRepository;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

  @Autowired
  AccountsRepository accountsRepository;

  public List<Account> getAccounts() {
    UserAccounts userAccounts = accountsRepository.findFirstByUserId("userid");  // TODO replace
    if (userAccounts == null) {
      return new ArrayList<>();
    } else {
      ArrayList<Account> result = new ArrayList<>();
      for (HashMap<String, String> accountMap:userAccounts.getAccounts()) {
        Account account = new Account();
        account.setName(accountMap.get("name"));
        account.setAccountId(accountMap.get("accountId"));
        result.add(account);
      }
      return result;
    }
  }

  public Account createAccount(@NotNull String accountName) {
    UserAccounts userAccounts = accountsRepository.findFirstByUserId("userid");
    if (userAccounts == null) {
      userAccounts = new UserAccounts();
      userAccounts.setUserId("userid");
    }
    List<HashMap<String, String>> existingAccounts = userAccounts.getAccounts();
    if (existingAccounts == null) {
      existingAccounts = new ArrayList<>();
      userAccounts.setAccounts(existingAccounts);
    }
    HashMap<String, String> accountMap = new HashMap<>();
    String id = UUID.randomUUID().toString();
    accountMap.put("name", accountName);
    accountMap.put("accountId", id);
    Account result = new Account();
    result.setAccountId(id);
    result.setName(accountName);
    existingAccounts.add(accountMap);
    accountsRepository.save(userAccounts);
    return result;
  }
}
