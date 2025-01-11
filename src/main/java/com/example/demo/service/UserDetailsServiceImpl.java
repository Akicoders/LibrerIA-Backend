package com.example.demo.service;

import com.example.demo.business.RoleRepository;
import com.example.demo.business.UsuarioRepository;
import com.example.demo.controller.dto.AuthCreateUserRequest;
import com.example.demo.controller.dto.AuthLoginRequest;
import com.example.demo.controller.dto.AuthResponse;
import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;


    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = this.authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username,"User Logged successfull", accessToken, true);
        return authResponse;
    }

    public Authentication authenticate(String email, String password) {
        UserDetails userDetails = this.loadUserByUsername(email);
        if (userDetails == null){
            throw new BadCredentialsException("Invalid email or password. ");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password. ");
        }

        return new UsernamePasswordAuthenticationToken(email,userDetails.getPassword(),userDetails.getAuthorities());
    }

    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
        Usuario user  = usuarioRepository.findByEmail(gmail).orElseThrow(() -> new UsernameNotFoundException(STR."El usuario con el email\{gmail}no existe "));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRols()
                .forEach(rol -> authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol())));
        user.getRols()
                .stream().flatMap(rol -> rol.getPermisos().stream())
                .forEach(permssion -> authorities.add(new SimpleGrantedAuthority(permssion.getName())));

        return new User(user.getEmail(), user.getContrasena(), user.getIsEnable(), user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(), authorities );
    }


    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest){
        String name = authCreateUserRequest.name();
        String last = authCreateUserRequest.lastName();
        String password = authCreateUserRequest.password();
        String email = authCreateUserRequest.email();
        List<String> roleRequest = authCreateUserRequest.roleRequest().rolesListName();
        log.info(roleRequest.toString());
        Set<Rol> rols = roleRepository.findRolsByRolIn(roleRequest).stream().collect(Collectors.toSet());


        if (rols.isEmpty()){
            throw new IllegalStateException("Los roles especificados no existen ");
        }

        Usuario user = Usuario.builder()
                .nombre(name)
                .apellido(last)
                .email(email)
                .contrasena(passwordEncoder.encode(password))
                .rols(rols)
                .isEnable(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .fechaRegistro(LocalDate.now())
                .build();

        Usuario userCreated = usuarioRepository.save(user);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userCreated.getRols().forEach(rol -> authorities.add(new SimpleGrantedAuthority("ROLE_" .concat(rol.getRol().name()))));

        userCreated.getRols()
                .stream()
                .flatMap(rol -> rol.getPermisos().stream())
                .forEach(permssion -> authorities.add(new SimpleGrantedAuthority(permssion.getName())));

        Authentication authentication = new UsernamePasswordAuthenticationToken(userCreated.getEmail(), userCreated.getContrasena(), authorities);

       String accessToken = jwtUtils.createToken(authentication);

       AuthResponse authResponse = new AuthResponse(userCreated.getEmail(), "User Created Sucessfull",accessToken,true);
        return authResponse;
    }
}
