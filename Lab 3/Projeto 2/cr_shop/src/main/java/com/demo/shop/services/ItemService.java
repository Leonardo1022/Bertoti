package com.demo.shop.services;

import com.demo.shop.models.Item;
import com.demo.shop.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> listarTodos() {return itemRepository.findAll();}

    public Item buscarPorId(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
    }

    public Item salvar(Item item) {
        return itemRepository.save(item);
    }

    public void deletar(Long id) {
        itemRepository.deleteById(id);
    }
}
