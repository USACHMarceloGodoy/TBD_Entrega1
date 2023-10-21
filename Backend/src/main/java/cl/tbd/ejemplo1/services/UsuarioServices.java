package cl.tbd.ejemplo1.services;

import cl.tbd.ejemplo1.Repository.*;
import cl.tbd.ejemplo1.errors.*;
import cl.tbd.ejemplo1.models.*;
import cl.tbd.ejemplo1.config.JwtService;
import cl.tbd.ejemplo1.request.*;
import cl.tbd.ejemplo1.Repository.*;
import lombok.RequiredArgsConstructor;
import cl.tbd.ejemplo1.errors.ApiErrorException;
import cl.tbd.ejemplo1.models.Rol;
import cl.tbd.ejemplo1.models.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import cl.tbd.ejemplo1.request.AuthenticationRequest;
import cl.tbd.ejemplo1.request.RegisterRequest;
import cl.tbd.ejemplo1.request.UpdateUsuarioRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class UsuarioServices {
    private UsuarioRepositoryImp usuarioRepository;
    private RolRepositoryImp rolRepository;
    private InstitucionRepositoryImp institucionRepository;
    private VoluntarioRepositoryImp voluntarioRepository;
    private TareaRepositoryImp tareaRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }
public void createUsuario(Map<String, Object> request) {
        try{
            if(usuarioRepository.findByEmail(request.getEmail()) != null){
                throw new ApiErrorException("El correo electronico ya existe.");
            }
            Usuario usuario = new Usuario();
            usuario.setNombre(request.getNombre());
            usuario.setApellido(request.getApellido());
            usuario.setEmail(request.getEmail());
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
            Usuario usuarioBD = usuarioRepository.save(usuario);
            //guardar roles
            List<Rol> roles = new ArrayList<>();
            Rol rol = rolRepository.findByNombre("ROLE_VOLUNTARIO");
            roles.add(rol);
            Voluntario voluntario = new Voluntario();
            voluntario.setUsuario(usuarioBD);
            voluntarioRepository.save(voluntario);
            usuarioBD.setRoles(roles);
            usuarioRepository.save(usuarioBD);
        }catch (ApiErrorException e){
            throw new ApiErrorException(e.getMessage());
        }catch (Exception e){
            throw new ApiErrorException("Error al crear usuario.");
        }
    }
    public Usuario getUsuario(int id) {
        return usuarioRepository.findById(id);
    }
    public String authentication(AuthenticationRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail());

        System.out.println("AUTH EMAIL = " + usuario);

        if(usuario == null){
            throw new ApiErrorException("El usuario no existe.");
        }

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Rol rol = rolRepository.findById(request.getIdRol());
        Map<String, Object> extraClaims = jwtService.createExtraClaimWithIdAndRol(usuario.getId(), rol);
        return jwtService.generateToken(extraClaims, usuario);
    }
}
