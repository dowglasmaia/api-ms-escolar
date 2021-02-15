package com.maia.mspagamento.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.maia.mspagamento.entity.Produto;
import com.maia.mspagamento.entity.vo.ProdutoVO;
import com.maia.mspagamento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive (@Payload ProdutoVO produtoVO) {
		produtoRepository.save(Produto.create(produtoVO));
	}
	
	

}
