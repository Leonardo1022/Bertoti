package com.demo.shop.controllers;

import com.demo.shop.dto.CompraRequestDTO;
import com.demo.shop.models.Compra;
import com.demo.shop.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class CompraController {
    @Autowired
    private CompraService service;

    @PostMapping
    public Compra confirmar(@RequestBody CompraRequestDTO dto) {
        return service.confirmar(dto);
    }
}
