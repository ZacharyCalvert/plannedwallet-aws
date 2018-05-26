package com.plannedwallet.accounts;

import com.plannedwallet.accounts.models.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountsApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testAccountSaved() {
		ResponseEntity<Account[]> accounts = restTemplate.getForEntity("/accounts", Account[].class);
		assertTrue(accounts.getStatusCode().is2xxSuccessful());
		ResponseEntity<Account> account = restTemplate.postForEntity("/accounts", "TestAccount", Account.class);
		assertTrue(account.getStatusCode().is2xxSuccessful());
		assertEquals("TestAccount", account.getBody().getName());
		accounts = restTemplate.getForEntity("/accounts", Account[].class);
		assertTrue(accounts.getStatusCode().is2xxSuccessful());
		assertTrue(accounts.getBody().length > 0);
	}
}
