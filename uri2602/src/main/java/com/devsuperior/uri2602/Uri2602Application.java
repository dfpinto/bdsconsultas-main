package com.devsuperior.uri2602;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import com.devsuperior.uri2602.repositories.CustomerRepository;

@SpringBootApplication
public class Uri2602Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2602Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<CustomerMinProjection> result1 = customerRepository.search1("RS");
		List<CustomerMinDTO> dtos = result1.stream().map(x -> new CustomerMinDTO((x))).collect(Collectors.toList());
		System.out.println("RESULTADO SQL RAIZ:");
		for (CustomerMinDTO dto : dtos) {
			System.out.println(dto.getName());
		}

		System.out.println("\n");

		List<CustomerMinDTO> result2 = customerRepository.search2("RS");
		System.out.println("RESULTADO JPQL DTO:");
		for (CustomerMinDTO dto : result2) {
			System.out.println(dto.getName());
		}

		System.out.println("\n");

		List<Customer> result3 = customerRepository.search3("RS");
		System.out.println("RESULTADO JPQL ENTITY:");
		for (Customer entity : result3) {
			System.out.println(entity.getName());
		}
	}
}
