package com.maia.mspagamento.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.maia.mspagamento.entity.vo.VendaVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "venda")
@Entity
public class Venda implements Serializable {
	private static final long serialVersionUID = 6352465870933839313L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data", nullable = false)
	private Date data;
	
	@Column(name = "valor_total", nullable = false,scale = 2, precision = 18, length = 10 )
	private Double valorTotal;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
	private Set<ProdutoVenda>produtos =  new HashSet<>();
	
	/**@since converte Objeto VendaVO para Venda*/
	public static Venda create(VendaVO vendaVO) {
		 return new ModelMapper().map(vendaVO, Venda.class);
	}

}
