package com.gilsontsc.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gilsontsc.crud.data.vo.ProdutoVO;
import com.gilsontsc.crud.entity.Produto;
import com.gilsontsc.crud.exception.ResourceNotFoundException;
import com.gilsontsc.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private final ProdutoRepository repository;

	@Autowired
	public ProdutoService(ProdutoRepository repository) {
		this.repository = repository;
	}
	
	public ProdutoVO create(ProdutoVO produtoVO) {
		Produto produto = this.repository.save(Produto.create(produtoVO));
		return ProdutoVO.create(produto);
	}
	
	public Page<ProdutoVO> findAll(Pageable pageable){
		var page = this.repository.findAll(pageable);
		return page.map(this::convertToprodutoVO);
	}
	
	public ProdutoVO findbyId(Long id) {
		var entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para o id " + id + "."));
		return ProdutoVO.create(entity);
		
	}
	
	public ProdutoVO update(ProdutoVO produtoVO) {
		final Optional<Produto> opProduto = this.repository.findById(produtoVO.getId());
		
		if(opProduto.isPresent()) {
			new ResourceNotFoundException("Produto não encontrado");
		}
		
		return ProdutoVO.create(this.repository.save(opProduto.get()));
	}
	
	public void delete(Long id) {
		var entity = this.repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado para o id " + id + "."));
		this.repository.delete(entity);
	}

	private ProdutoVO convertToprodutoVO(Produto produto) {
		return ProdutoVO.create(produto);
	}
}