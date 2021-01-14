package com.gilsontsc.pagamento.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.gilsontsc.pagamento.vo.ProdutoVendaVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_produto_venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVenda implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_produto", nullable = false, length = 10)
	private Long idProduto;
	
	@Column(name = "quantidade", nullable = false, length = 10)
	private Integer quantidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_venda")
	private Venda venda;
	
	public static ProdutoVenda create(ProdutoVendaVO produtoVendaVO) {
		return new ModelMapper().map(produtoVendaVO, ProdutoVenda.class);
	}

}