package com.dmaia.mscrud.entity.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;

import com.dmaia.mscrud.entity.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonPropertyOrder({ "id", "nome", "preco", "estoque", })
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProdutoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("nome")
	private String nome;

	@JsonProperty("estoque")
	private String estoque;

	@JsonProperty("preco")
	private Double preco;

	public static ProdutoVO converteToProdutoVO(Produto produto) {
		return new ModelMapper().map(produto, ProdutoVO.class);
	}

}
