package com.demo.shop.services;

import com.demo.shop.models.Customizacao;
import com.demo.shop.repositories.CustomizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomizacaoService {
    @Autowired
    CustomizacaoRepository customizacaoRepository;

    public List<Customizacao> listarTodos() {return customizacaoRepository.findAll();}

    public Customizacao buscarPorId(Long id) {
        return customizacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customização não encontrado"));
    }

    public Customizacao salvar(Customizacao customizacao) {
        return customizacaoRepository.save(customizacao);
    }

    public void deletar(Long id) {
        customizacaoRepository.deleteById(id);
    }
}
