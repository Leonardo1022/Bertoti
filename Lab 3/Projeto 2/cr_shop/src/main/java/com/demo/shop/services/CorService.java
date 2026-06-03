package com.demo.shop.services;

import com.demo.shop.models.Cor;
import com.demo.shop.repositories.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorService {
    @Autowired
    CorRepository corRepository;

    public List<Cor> listarTodos() {return corRepository.findAll();}

    public Cor buscarPorId(String codigo) {
        return corRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException("cor não encontrado"));
    }

    public Cor salvar(Cor compra) {
        return corRepository.save(compra);
    }

    public void deletar(String codigo) {
        corRepository.deleteById(codigo);
    }
}
