package com.maia.mspagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maia.mspagamento.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
	

}
