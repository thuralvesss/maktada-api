package br.com.maktaba.controler;

import br.com.maktaba.model.Usuario;
import br.com.maktaba.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/cadastro")
    public String exibirCadastro() {
        return "cadastro";
    }

    @GetMapping("/login")
    public String exibirLogin() {
        return "login";
    }

    @PostMapping("/registrar")
    public String registrar(Usuario usuario) {
        repository.save(usuario);
        return "redirect:/usuarios/login";
    }
}