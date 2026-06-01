package com.demo.shop.repositories;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AmigurumiAcessorioRepository {
    List<AmigurumiAcessorio> findByAmigurumiId(Long amigurumiId);
}
