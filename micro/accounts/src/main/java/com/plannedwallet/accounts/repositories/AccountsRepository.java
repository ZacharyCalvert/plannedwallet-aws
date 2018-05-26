package com.plannedwallet.accounts.repositories;

import com.plannedwallet.accounts.models.UserAccounts;
import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface AccountsRepository extends CrudRepository<UserAccounts, String> {

  UserAccounts findFirstByUserId(String userId);
}