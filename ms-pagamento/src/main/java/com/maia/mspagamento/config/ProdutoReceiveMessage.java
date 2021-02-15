package com.maia.mspagamento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maia.mspagamento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	
	

}
