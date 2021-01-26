package com.dmaia.mscrud.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dmaia.mscrud.entity.vo.ProdutoVO;

@Service
public interface IProdutoServices {

	public ProdutoVO create(ProdutoVO obj);
	
	public ProdutoVO update(ProdutoVO obj);
	
	public Page<ProdutoVO>findAll(Pageable pageable);
	
	public ProdutoVO findById(Long id);
	
	public void delete(Long id);
}
