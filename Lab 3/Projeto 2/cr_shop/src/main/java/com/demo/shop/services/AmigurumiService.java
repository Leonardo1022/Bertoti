package com.demo.shop.services;

import com.demo.shop.models.Amigurumi;
import com.demo.shop.models.Cor;
import com.demo.shop.repositories.AmigurumiRepository;
import com.demo.shop.repositories.CorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmigurumiService {

    @Autowired
    private AmigurumiRepository amigurumiRepository;

    @Autowired
    private CorRepository corRepository;

    public List<Amigurumi> listarTodos() {
        return amigurumiRepository.findAll();
    }

    public Amigurumi buscarPorId(Long id) {
        return amigurumiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amigurumi não encontrado"));
        //  ↑ findById retorna Optional, então tratamos o caso de não encontrar
    }

    public Amigurumi salvar(Amigurumi amigurumi) {
        return amigurumiRepository.save(amigurumi);
    }

    public Amigurumi atualizar(Long id, Amigurumi dados) {
        Amigurumi existente = amigurumiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Amigurumi não encontrado"));

        existente.setNome(dados.getNome());
        existente.setFoto(dados.getFoto());
        existente.setPreco(dados.getPreco());
        return amigurumiRepository.save(existente);
    }

    public void deletar(Long id) {
        amigurumiRepository.deleteById(id);
    }

//——— Tabela Amigurumi_Cor
    public List<Amigurumi> buscarAmigurumisPorCor(String corCodigo) {
        return amigurumiRepository.findByCorCodigo(corCodigo);
    }
    // Criar pedido com produtos
    public Amigurumi criarAmigurumi(Amigurumi amigurumi, List<String> corCodigos) {

        // 1. Busca os produtos pelos IDs
        List<Cor> cores = corRepository.findAllById(corCodigos);

        // 2. Cria o amigurumi e associa as cores
        amigurumi.setCores(cores);  // JPA cuida de popular a tabela intermediária

        // 3. Salva — o JPA insere ou atualiza em amigurumis E em Amigurumi_Cor automaticamente
        return amigurumiRepository.save(amigurumi);
    }

    // Adicionar cor a um amigurumi existente
    public Amigurumi adicionarCor(Long amigurumiId, String corCodigo) {

        Amigurumi amigurumi = amigurumiRepository.findById(amigurumiId)
                .orElseThrow(() -> new RuntimeException("Amigurumi não encontrado"));

        Cor cor = corRepository.findById(corCodigo)
                .orElseThrow(() -> new RuntimeException("Cor não encontrada"));

        amigurumi.getCores().add(cor);  // adiciona na lista
        return amigurumiRepository.save(amigurumi); // JPA insere na tabela intermediária
    }

    // Remover cor de um amigurumi
    public Amigurumi removerCor(Long amigurumiId, String corCodigo) {

        Amigurumi amigurumi = amigurumiRepository.findById(amigurumiId)
                .orElseThrow(() -> new RuntimeException("Amigurumi não encontrado"));

        amigurumi.getCores().removeIf(p -> p.getCodigo().equals(corCodigo));
        return amigurumiRepository.save(amigurumi);
    }

//——— Tabela Amigurumi_Acessorio

}
