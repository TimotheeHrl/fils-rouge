package com.filRouge.filRouge;

import com.filRouge.filRouge.model.Customer;
import com.filRouge.filRouge.service.CustomerService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import com.filRouge.filRouge.model.AppUserRole;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class FilRougeApplication {

	public FilRougeApplication(CustomerService customerService) {
		this.customerService = customerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilRougeApplication.class, args);
	}

	final CustomerService customerService;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public void run(String... params) throws Exception {
		Customer admin = new Customer();
		admin.setPassword("admin");
		admin.setMail("admin@email.com");
		admin.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN)));

		customerService.signup(admin);

		Customer client = new Customer();
		client.setPassword("client");
		client.setMail("client@email.com");
		client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_CLIENT)));

		customerService.signup(client);
	}
}
