package com.demo.shop.services;

import com.demo.shop.models.Acessorio;
import com.demo.shop.models.Amigurumi;
import com.demo.shop.repositories.AcessorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcessorioService {
    @Autowired
    AcessorioRepository acessorioRepository;

    public List<Acessorio> listarTodos() {return acessorioRepository.findAll();}

    public Acessorio buscarPorId(Long id) {
        return acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessório não encontrado"));
    }

    public Acessorio salvar(Acessorio acessorio) {
        return acessorioRepository.save(acessorio);
    }

    public void deletar(Long id) {
        acessorioRepository.deleteById(id);
    }
}