package com.demo.shop.controllers;

import com.demo.shop.models.Amigurumi;
import com.demo.shop.services.AmigurumiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amigurumis")
public class AmigurumiController {
    @Autowired
    private AmigurumiService service;

    @GetMapping           // GET /amigurumis
    public List<Amigurumi> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")  // GET /amigurumis/1
    public Amigurumi buscar(@PathVariable Long id) {
        //              ↑ pega o {id} da URL
        return service.buscarPorId(id);
    }

    @PostMapping          // POST /amigurumis  (corpo JSON)
    public Amigurumi criar(@RequestBody Amigurumi amigurumi) {
        //              ↑ converte o JSON do body para objeto Java
        return service.salvar(amigurumi);
    }

    @PutMapping("/{id}")  // PUT /amigurumis/1
    public Amigurumi atualizar(@PathVariable Long id, @RequestBody Amigurumi amigurumi) {
        return service.atualizar(id, amigurumi);
    }

    @DeleteMapping("/{id}") // DELETE /amigurumis/1
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
