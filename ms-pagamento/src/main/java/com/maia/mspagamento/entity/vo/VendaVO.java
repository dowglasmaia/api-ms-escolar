package com.maia.mspagamento.entity.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.maia.mspagamento.entity.ProdutoVenda;
import com.maia.mspagamento.entity.Venda;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@JsonPropertyOrder({ "id", "data", "valor", "produtos" })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class VendaVO  extends RepresentationModel<VendaVO>  implements Serializable {
	private static final long serialVersionUID = 6352465870933839313L;
	
	
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("data")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date data;
	
	@JsonProperty("produtos")
	private Set<ProdutoVenda>produtos =  new HashSet<>();
	
	@JsonProperty("valor")
	private Double valorTotal;
	

	/**@since converte Objeto Venda para VendaVO*/
	public static VendaVO create(Venda venda) {
		 return new ModelMapper().map(venda, VendaVO.class);
	}
	
	
	
}
