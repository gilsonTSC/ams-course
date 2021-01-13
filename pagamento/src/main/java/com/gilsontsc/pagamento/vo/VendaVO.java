package com.gilsontsc.pagamento.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gilsontsc.pagamento.entity.ProdutoVenda;
import com.gilsontsc.pagamento.entity.Venda;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable{

private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("data")
	private Date data;
	
	@JsonProperty("produtos")
	private List<ProdutoVenda> produtos;
	
	@JsonProperty("valorTotal")
	private Double valorTotal;
	
	public static VendaVO create(Venda venda) {
		return new ModelMapper().map(venda, VendaVO.class);
	}
	
}