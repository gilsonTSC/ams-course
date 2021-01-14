package com.gilsontsc.pagamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gilsontsc.pagamento.entity.ProdutoVenda;
import com.gilsontsc.pagamento.entity.Venda;
import com.gilsontsc.pagamento.exception.ResourceNotFoundException;
import com.gilsontsc.pagamento.repository.ProdutoVendaRepository;
import com.gilsontsc.pagamento.repository.VendaRepository;
import com.gilsontsc.pagamento.vo.VendaVO;

@Service
public class VendaService {

	private final VendaRepository repository;
	private final ProdutoVendaRepository produtoVendaRepository;

	@Autowired
	public VendaService(VendaRepository repository, ProdutoVendaRepository produtoVendaRepository) {
		this.repository = repository;
		this.produtoVendaRepository = produtoVendaRepository;
	}
	
	public VendaVO create(VendaVO vendaVO) {
		Venda venda = this.repository.save(Venda.create(vendaVO));
		List<ProdutoVenda> produtoSalvos = new ArrayList<>();
		vendaVO.getProdutos().forEach(p -> {
			ProdutoVenda produtoVenda = ProdutoVenda.create(p);
			produtoVenda.setVenda(venda);
			produtoSalvos.add(this.produtoVendaRepository.save(produtoVenda));
		});
		venda.setProdutos(produtoSalvos);
		return VendaVO.create(venda);
	}
	
	public Page<VendaVO> findAll(Pageable pageable){
		var page = this.repository.findAll(pageable);
		return page.map(this::convertToVendaVO);
	}
	
	public VendaVO findbyId(Long id) {
		var entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Venda não encontrado para o id " + id + "."));
		return VendaVO.create(entity);
		
	}
	
	public VendaVO update(VendaVO vendaVO) {
		final Optional<Venda> opVenda = this.repository.findById(vendaVO.getId());
		
		if(opVenda.isPresent()) {
			new ResourceNotFoundException("Venda não encontrado");
		}
		
		return VendaVO.create(this.repository.save(opVenda.get()));
	}
	
	public void delete(Long id) {
		var entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Venda não encontrado para o id " + id + "."));
		this.repository.delete(entity);
	}
	
	private VendaVO convertToVendaVO(Venda venda) {
		return VendaVO.create(venda);
	}
	
}