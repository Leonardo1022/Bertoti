package com.demo.shop.config;

import com.demo.shop.models.Amigurumi;
import com.demo.shop.repositories.AmigurumiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AmigurumiRepository amigurumiRepository;

    @Override
    public void run(String... args) {
        if (amigurumiRepository.count() > 0) return; // evita duplicar a cada restart

        Amigurumi sapo = new Amigurumi("Sapo", "sapo.jpg", new BigDecimal("15.00"), List.of(), List.of());
        Amigurumi pato = new Amigurumi("Pato", "pato.jpg", new BigDecimal("15.00"), List.of(), List.of());
        amigurumiRepository.save(sapo);
        amigurumiRepository.save(pato);

    }
}
