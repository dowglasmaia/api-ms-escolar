package com.maia.mspagamento.services.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maia.mspagamento.entity.ProdutoVenda;
import com.maia.mspagamento.entity.Venda;
import com.maia.mspagamento.entity.vo.VendaVO;
import com.maia.mspagamento.exceptions.ResourceNotFoundException;
import com.maia.mspagamento.repository.ProdutoVendaRepository;
import com.maia.mspagamento.repository.VendaRepository;
import com.maia.mspagamento.services.IVendaServices;

@Service
public class VendaServices implements IVendaServices {

	private final VendaRepository vendaRepository;
	private final ProdutoVendaRepository produtoVendaRepository;
	
	@Autowired
	public VendaServices(VendaRepository repo,ProdutoVendaRepository produtoVendaRepository) {
		this.vendaRepository = repo;
		this.produtoVendaRepository = produtoVendaRepository;
	}

	@Override
	public VendaVO create(VendaVO obj) {
		obj.setData(new Date());
		Venda venda = vendaRepository.save(Venda.create(obj));
		
		Set<ProdutoVenda> produtosSalvos = new  HashSet<>();
		obj.getProdutos().forEach(p ->{
			ProdutoVenda pv = ProdutoVenda.create(p);
			pv.setVenda(venda);
			produtosSalvos.add(produtoVendaRepository.save(pv));
		});
		venda.setProdutos(produtosSalvos);
		return VendaVO.create(venda);
	}

	@Override
	public Page<VendaVO> findAll(Pageable pageable) {
		var page = vendaRepository.findAll(pageable);
		return page.map(this::convertToVendaVO);
	}

	@Override
	public VendaVO findById(Long id) {
		var entity = vendaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Venda não encontrado para o ID " + id));
		return VendaVO.create(entity);
	}

	@Override
	public void delete(Long id) {
		var entity = Venda.create(this.findById(id));
		vendaRepository.delete(entity);

	}

	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}

}
