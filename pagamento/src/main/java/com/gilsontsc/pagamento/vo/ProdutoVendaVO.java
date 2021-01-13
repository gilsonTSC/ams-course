package com.gilsontsc.pagamento.vo;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gilsontsc.pagamento.entity.ProdutoVenda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({"id", "idProduto", "quantidade"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("idProduto")
	private Long idProduto;
	
	@JsonProperty("quantidade")
	private Integer quantidade;
	
	public static ProdutoVendaVO create(ProdutoVenda produtoVenda) {
		return new ModelMapper().map(produtoVenda, ProdutoVendaVO.class);
	}
	
}