package mx.edu.utez.proyecto1E.service.Usuario;


import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestCreateUserDto;
import mx.edu.utez.proyecto1E.controller.usuario.dto.RequestUpdateUsuarioDto;
import mx.edu.utez.proyecto1E.exception.customExceptions.CustomBadException;
import mx.edu.utez.proyecto1E.model.usuario.Roles;
import mx.edu.utez.proyecto1E.model.usuario.Usuario;
import mx.edu.utez.proyecto1E.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Usuario createUser(RequestCreateUserDto payload) {
        Optional<Usuario> usuarioExist = usuarioRepository.findByUsername(payload.getUsername());
        if (usuarioExist.isPresent()) {
            throw new CustomBadException("El username ya existe");
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(payload.getUsername());
        nuevoUsuario.setDescripcion(payload.getDescripcion());
        nuevoUsuario.setPassword(payload.getPassword());
        nuevoUsuario.setRol(Roles.valueOf(payload.getRol()));
        nuevoUsuario.set_enable(true);

        return usuarioRepository.save(nuevoUsuario);
    }


    public Usuario getUserById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new CustomBadException("El usuario con ID " + id + " no existe"));
    }

    public Usuario changeUserStatus(Long id, boolean enable) {
        Usuario usuario = getUserById(id);
        usuario.set_enable(enable);
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUser(Long id, RequestUpdateUsuarioDto payload) {
        Usuario usuario = getUserById(id);

        Optional<Usuario> existingUsername = usuarioRepository.findByUsername(payload.getUsername());
        if (existingUsername.isPresent() && !existingUsername.get().getId().equals(id)) {
            throw new CustomBadException("El username ya se encuentra en uso");
        }

        usuario.setUsername(payload.getUsername());
        usuario.setDescripcion(payload.getDescripcion());

        return usuarioRepository.save(usuario);
    }
}