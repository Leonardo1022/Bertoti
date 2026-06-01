package com.demo.shop.repositories;

import com.demo.shop.models.Amigurumi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmigurumiRepository extends JpaRepository<Amigurumi, Long> {
    List<Amigurumi> findByCorId(String corCodigo);
    List<Amigurumi> findByAcessorioId(Long acessorioId);
}
