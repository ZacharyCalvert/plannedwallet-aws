package com.plannedwallet.accounts.config;

import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnMissingBean(AmazonDynamoDB.class)
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.plannedwallet.accounts.repositories")

public class DynamoDBConfig {

  @Bean
  public AmazonDynamoDB amazonDynamoDB() {
    AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();
    builder.setEndpointConfiguration(new EndpointConfiguration("http://192.168.99.100:32065", null));
    return builder.build();
  }

}
