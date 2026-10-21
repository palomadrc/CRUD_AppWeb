package mx.edu.utez.proyecto1E.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUsuarioDto;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.service.Usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin({"*"})
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> getallUsers() {
        return ResponseEntity
                .status(200)
                .body(usuarioService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<Usuario> CreateUser(@RequestBody @Valid RequestCreateUserDto payload) {
        return ResponseEntity
                .status(201)
                .body(usuarioService.createUser(payload));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(200)
                .body(usuarioService.getUserById(id));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Usuario> changeStatus(@PathVariable Long id, @RequestParam boolean enable) {
        return ResponseEntity
                .status(200)
                .body(usuarioService.changeUserStatus(id, enable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @Valid @RequestBody RequestUpdateUsuarioDto payload) {
        return ResponseEntity
                .status(200)
                .body(usuarioService.updateUser(id, payload));
    }
}