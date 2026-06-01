package com.demo.shop.repositories;

import com.demo.shop.models.Acessorio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessorioRepository extends JpaRepository<Acessorio, Long> {
}
