package com.pop.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pop.jpa.UpperCaseJpa;

public interface UpperCaseRepository extends JpaRepository <UpperCaseJpa, String> {
	
	public Optional<UpperCaseJpa> findByOriginal (String text);

}
