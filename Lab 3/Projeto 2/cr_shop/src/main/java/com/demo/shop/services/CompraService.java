package com.demo.shop.services;

import com.demo.shop.dto.CompraRequestDTO;
import com.demo.shop.models.Amigurumi;
import com.demo.shop.models.Compra;
import com.demo.shop.models.Item;
import com.demo.shop.models.Usuario;
import com.demo.shop.repositories.AmigurumiRepository;
import com.demo.shop.repositories.CompraRepository;
import com.demo.shop.repositories.ItemRepository;
import com.demo.shop.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {
    @Autowired
    CompraRepository compraRepository;

    @Autowired
    AmigurumiRepository amigurumiRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ItemRepository itemRepository;

    public List<Compra> listarTodos() {return compraRepository.findAll();}

    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrado"));
    }

    public Compra salvar(Compra compra) {
        return compraRepository.save(compra);
    }

    public void deletar(Long id) {
        compraRepository.deleteById(id);
    }

    public Compra confirmar(CompraRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Compra compra = new Compra(LocalDateTime.now(), usuario, new ArrayList<>());
        compraRepository.save(compra);

        for (CompraRequestDTO.ItemRequestDTO itemDTO : dto.getItens()) {
            Amigurumi amigurumi = amigurumiRepository.findById(itemDTO.getAmigurumiId())
                    .orElseThrow(() -> new RuntimeException("Amigurumi não encontrado"));

            Item item = new Item(itemDTO.getQuantidade(), itemDTO.getPreco(), amigurumi, compra);
            itemRepository.save(item);
            compra.getItens().add(item);
        }

        return compra;
    }
}
