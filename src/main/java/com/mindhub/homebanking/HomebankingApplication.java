package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository) {
		return (args) -> {

			Client client1 = new Client("Melba", "Morel","melba@mindhub.com");
			Client client2 = new Client("Paola", "Gomez","paomeli79@gmail.com");

			clientRepository.save(client1);
			clientRepository.save(client2);

			LocalDate today = LocalDate.now();
			LocalDate tomorrow = today.plusDays(1);
			LocalDate nextMonth = today.minusDays(1);
			Account account1 = new Account("VIN001",today,5000.0);
			Account account2 = new Account("VIN002",tomorrow,7500.0);
			Account account3 = new Account("VIN003",nextMonth,10000.0);

			client1.addAccount(account1);
			client1.addAccount(account2);
			client2.addAccount(account3);

			accountRepository.save(account1);
			accountRepository.save(account2);
			accountRepository.save(account3);

			clientRepository.save(client1);
			clientRepository.save(client2);

		};
	}

}
