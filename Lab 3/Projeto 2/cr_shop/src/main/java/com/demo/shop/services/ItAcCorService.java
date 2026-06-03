package com.demo.shop.services;

import com.demo.shop.models.ItAcCor;
import com.demo.shop.models.ItAcCorId;
import com.demo.shop.repositories.ItAcCorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItAcCorService {
    @Autowired
    ItAcCorRepository itAcCorRepository;

    public List<ItAcCor> listarTodos() {return itAcCorRepository.findAll();}

    public ItAcCor buscarPorId(ItAcCorId id) {
        return itAcCorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customização não encontrado"));
    }

    public ItAcCor salvar(ItAcCor itAcCor) {
        return itAcCorRepository.save(itAcCor);
    }

    public void deletar(ItAcCorId id) {
        itAcCorRepository.deleteById(id);
    }
}
