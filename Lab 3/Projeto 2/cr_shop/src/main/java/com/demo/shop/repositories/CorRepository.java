package com.demo.shop.repositories;

import com.demo.shop.models.Cor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CorRepository extends JpaRepository<Cor, String> {
}
