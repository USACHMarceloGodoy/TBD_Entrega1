package cl.tbd.ejemplo1.controller;

import cl.tbd.ejemplo1.errors.ApiErrorException;
import cl.tbd.ejemplo1.models.Usuario;
import cl.tbd.ejemplo1.request.AuthenticationRequest;
import cl.tbd.ejemplo1.request.RegisterRequest;
import cl.tbd.ejemplo1.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioServices usuarioServices;

    @GetMapping("/")
    public Iterable<Usuario> getUsuarios() {
        return usuarioServices.getUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable int id) {
        return usuarioServices.getUsuario(id);
    }

    @PostMapping("/registro")
    public void createUsuario(@RequestBody RegisterRequest request) {
        usuarioServices.createUsuario(request);
    }

    @PostMapping("/autenticacion")
    public String authentication(@RequestBody AuthenticationRequest request) {
        return usuarioServices.authentication(request);
    }

    // Otros métodos de controlador para actualizaciones, eliminaciones, etc., si es necesario

    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<String> handleApiErrorException(ApiErrorException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
