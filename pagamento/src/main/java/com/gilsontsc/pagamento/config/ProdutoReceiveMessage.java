package com.gilsontsc.pagamento.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.gilsontsc.pagamento.entity.Produto;
import com.gilsontsc.pagamento.repository.ProdutoRepository;
import com.gilsontsc.pagamento.vo.ProdutoVO;

@Component
public class ProdutoReceiveMessage {

	private final ProdutoRepository produtoRepository;

	@Autowired
	public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive (@Payload ProdutoVO produtoVO){
		produtoRepository.save(Produto.create(produtoVO));
	}

}