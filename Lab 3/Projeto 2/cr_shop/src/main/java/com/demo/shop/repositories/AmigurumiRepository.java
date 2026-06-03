package com.demo.shop.repositories;

import com.demo.shop.models.Amigurumi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmigurumiRepository extends JpaRepository<Amigurumi, Long> {
    @Query("SELECT a FROM Amigurumi a JOIN a.cores c WHERE c.codigo = :corCodigo")
    List<Amigurumi> findByCorCodigo(@Param("corCodigo") String corCodigo);

    @Query("SELECT a FROM Amigurumi a JOIN a.acessorios ac WHERE ac.id = :acessorioId")
    List<Amigurumi> findByAcessorioId(@Param("acessorioId") Long acessorioId);
}
