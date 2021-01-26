package com.dmaia.mscrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dmaia.mscrud.entity.Produto;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {

}
