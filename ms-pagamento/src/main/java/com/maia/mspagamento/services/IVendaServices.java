package com.maia.mspagamento.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maia.mspagamento.entity.vo.VendaVO;



@Service
public interface IVendaServices {

	@Transactional
	public VendaVO create(VendaVO obj);
	
	@Transactional(readOnly = true)
	public Page<VendaVO>findAll(Pageable pageable);
	
	@Transactional(readOnly = true)
	public VendaVO findById(Long id);
	
	@Transactional
	public void delete(Long id);
}
