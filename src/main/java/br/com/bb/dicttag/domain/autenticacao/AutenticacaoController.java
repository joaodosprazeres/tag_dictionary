package br.com.bb.dicttag.domain.autenticacao;

import br.com.bb.dicttag.domain.usuario.DadosAutenticacao;
import br.com.bb.dicttag.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authenticaon = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
