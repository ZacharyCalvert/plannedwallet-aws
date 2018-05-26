package com.plannedwallet.accounts.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;

public class Account {

  private String accountId;

  private String name;

  @DynamoDBAttribute
  public String getAccountId() {
    return accountId;
  }

  @DynamoDBAttribute
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
