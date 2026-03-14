package br.com.maktaba.controler;

import br.com.maktaba.model.Usuario;
import br.com.maktaba.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios") // Este será o endereço oficial: localhost:8080/usuarios
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // Rota para mostrar todos os usuários cadastrados
    @GetMapping
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // Rota para salvar um novo usuário no banco de dados
    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }
}