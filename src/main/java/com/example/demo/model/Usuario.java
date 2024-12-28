package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Entity
@Table(name = "users")
public class Usuario  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 100)
    private String nombre;

    @Column(name = "lastName", length = 100, nullable = false)
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 100)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "El correo electrónico no tiene un formato válido")
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 12)
    private String contrasena;

    @Column(name = "registration_Date", nullable = false)
    private LocalDate fechaRegistro = LocalDate.now();

    @Column(name = "is_Enabled", nullable = false)
    @ColumnDefault("true")
    private Boolean isEnable = true;

    @Column(name = "account_No_expired", nullable = false)
    @ColumnDefault("true")
    private Boolean accountNonExpired = true;

    @Column(name = "account_No_locked", nullable = false)
    @ColumnDefault("true")
    private Boolean accountNonLocked = true;

    @Column(name = "credentials_No_Expired", nullable = false)
    @ColumnDefault("true")
    private Boolean credentialsNonExpired = true;

    @ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Rol> rols = new HashSet<>();




}