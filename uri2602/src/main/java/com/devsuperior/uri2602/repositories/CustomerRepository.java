package com.devsuperior.uri2602.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	@Query(nativeQuery = true, value = "SELECT NAME FROM CUSTOMERS C WHERE C.STATE = :state")
	List<CustomerMinProjection> search1(String state);

	@Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(obj.name) FROM Customer obj WHERE obj.state = :state")
	List<CustomerMinDTO> search2(String state);

	@Query("SELECT obj FROM Customer obj WHERE obj.state = :state")
	List<Customer> search3(String state);
}
