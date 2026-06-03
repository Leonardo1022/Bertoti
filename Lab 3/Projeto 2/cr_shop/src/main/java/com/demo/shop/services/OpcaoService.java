package com.demo.shop.services;

import com.demo.shop.models.Opcao;
import com.demo.shop.repositories.OpcaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpcaoService {
    @Autowired
    OpcaoRepository opcaoRepository;

    public List<Opcao> listarTodos() {return opcaoRepository.findAll();}

    public Opcao buscarPorId(Long id) {
        return opcaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opção não encontrado"));
    }

    public Opcao salvar(Opcao opcao) {
        return opcaoRepository.save(opcao);
    }

    public void deletar(Long id) {
        opcaoRepository.deleteById(id);
    }
}
