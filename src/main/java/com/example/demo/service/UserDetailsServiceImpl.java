package com.example.demo.service;

import com.example.demo.business.UsuarioRepository;
import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

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
}
