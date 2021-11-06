package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<MovieMinProjection> result1 = movieRepository.search1("Action");
		List<MovieMinDTO> dtos = result1.stream().map(x -> new MovieMinDTO((x))).collect(Collectors.toList());
		System.out.println("RESULTADO SQL RAIZ:");
		for (MovieMinDTO dto : dtos) {
			System.out.println(dto.getName());
		}

		System.out.println("\n");

		List<MovieMinDTO> result2 = movieRepository.search2("Action");
		System.out.println("RESULTADO JPQL DTO:");
		for (MovieMinDTO dto : result2) {
			System.out.println(dto.getName());
		}
	}
}
