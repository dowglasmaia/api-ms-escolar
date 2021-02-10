package com.dmaia.mscrud.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dmaia.mscrud.entity.Produto;
import com.dmaia.mscrud.entity.vo.ProdutoVO;
import com.dmaia.mscrud.exceptions.ResourceNotFoundException;
import com.dmaia.mscrud.message.ProdutoSendMessage;
import com.dmaia.mscrud.repository.IProdutoRepository;

@Service
public class ProdutoServices implements IProdutoServices {

	private final IProdutoRepository produtoRepository;
	private final ProdutoSendMessage produtoSendMessage; 

	@Autowired
	public ProdutoServices(IProdutoRepository repo,ProdutoSendMessage produtoSendMessage) {
		this.produtoRepository = repo;
		this.produtoSendMessage = produtoSendMessage;
	}

	@Override
	public ProdutoVO create(ProdutoVO obj) {
		Produto produto = produtoRepository.save(Produto.converteToProduto(obj));
		ProdutoVO produtoVO = ProdutoVO.converteToProdutoVO(produto);
		produtoSendMessage.sendMessage(produtoVO);
		return produtoVO;
	}

	@Override
	public Page<ProdutoVO> findAll(Pageable pageable) {
		var page = produtoRepository.findAll(pageable);
		return page.map(this::convertToProdutoVO);
	}

	@Override
	public ProdutoVO findById(Long id) {
		var entity = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("produto não encontrado para o ID " + id));
		return ProdutoVO.converteToProdutoVO(entity);
	}

	@Override
	public ProdutoVO update(ProdutoVO obj) {
		final Optional<Produto> produto = produtoRepository.findById(obj.getId());
		if (!produto.isPresent()) {
			new ResourceNotFoundException("produto não encontrado para o ID " + obj.getId());
		}

		return ProdutoVO.converteToProdutoVO( produtoRepository.save(Produto.converteToProduto(obj)) );
	}
	
	@Override
	public void delete(Long id) {
		this.findById(id);
		produtoRepository.deleteById(id);
		
	}
	// 30215252

	private ProdutoVO convertToProdutoVO(Produto produto) {
		return ProdutoVO.converteToProdutoVO(produto);
	}

	
}
