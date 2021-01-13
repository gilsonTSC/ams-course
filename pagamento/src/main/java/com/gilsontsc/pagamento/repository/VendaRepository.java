package com.gilsontsc.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gilsontsc.pagamento.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{

}