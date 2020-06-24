package com.exemplo.Big.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.Big.Model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	

}
