package com.example.demo;

import com.example.demo.business.UsuarioRepository;
import com.example.demo.model.Permiso;
import com.example.demo.model.Rol;
import com.example.demo.model.RolEnum;
import com.example.demo.model.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UsuarioRepository repo) {
		// Create Permissions
		Permiso permisoCrear = Permiso.builder().name("CREATE").build();
		Permiso permisoLeer = Permiso.builder().name("READ").build();
		Permiso permisoActualizar = Permiso.builder().name("UPDATE").build();
		Permiso permisoEliminar = Permiso.builder().name("DELETE").build();
		// Create Roles
		Rol rolAdmin = Rol.builder()
				.rol(RolEnum.ADMIN)
				.permisos(Set.of(permisoCrear, permisoLeer, permisoActualizar, permisoEliminar))
				.build();
		Rol rolDeveloper = Rol.builder()
				.permisos(Set.of(permisoCrear, permisoLeer, permisoActualizar))
				.rol(RolEnum.DEVELOPER)
				.build();
		Rol rolUser = Rol.builder()
				.permisos(Set.of(permisoLeer))
				.rol(RolEnum.USER)
				.build();
		return args -> {
			// Create Users
			Usuario akicoder = Usuario
					.builder()
					.email("akicoders@gmail.com")
					.apellido("Java Developer")
					.nombre("Akicoder")
					.contrasena("$2a$10$yl9E3EZcy4uaQs6hK8j/eenl1GzhK18OZNBXIT43Cgk0JiDet6FJq")
					.rols(Set.of(rolAdmin))
					.fechaRegistro(LocalDate.now())
					.isEnable(true)
					.credentialsNonExpired(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.build();

			Usuario paul = Usuario
					.builder()
					.email("Josecito@gmail.com")
					.apellido("Campos Terrones")
					.nombre("Jose Paul")
					.contrasena("$2a$10$yguv3spn.jk1V7HUjx.lTeIX0YMvL6h51rsVAlbNEGodBMYo2yVZG")
					.rols(Set.of(rolDeveloper))
					.fechaRegistro(LocalDate.now())
					.isEnable(true)
					.credentialsNonExpired(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.build();

			Usuario normal = Usuario
					.builder()
					.email("usuarioNormal@gmail.com")
					.apellido("Norma")
					.nombre("Ignacia")
					.contrasena("$2a$10$.mGVjVohmYcl9EGm36cyxue1heaBrcVq95jm4LRdbiKv72ZuET4ea")
					.rols(Set.of(rolUser))
					.fechaRegistro(LocalDate.now())
					.isEnable(true)
					.credentialsNonExpired(true)
					.accountNonExpired(true)
					.accountNonLocked(true)
					.build();

			repo.saveAll(List.of(normal, paul,akicoder));
		};
	}

}
