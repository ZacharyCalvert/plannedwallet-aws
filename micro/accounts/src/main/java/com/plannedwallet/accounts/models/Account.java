package com.plannedwallet.accounts.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public class Account {

  private String accountId;

  private String name;

  public String getAccountId() {
    return accountId;
  }

  public String getName() {
    return name;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public void setName(String name) {
    this.name = name;
  }
}
