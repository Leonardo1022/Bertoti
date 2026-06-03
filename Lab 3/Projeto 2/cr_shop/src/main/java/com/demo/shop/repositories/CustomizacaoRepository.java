package com.demo.shop.repositories;

import com.demo.shop.models.Customizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizacaoRepository extends JpaRepository<Customizacao, Long> {
}
