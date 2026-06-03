package com.demo.shop.services;

import com.demo.shop.models.Usuario;
import com.demo.shop.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }


}
