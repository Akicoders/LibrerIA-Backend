package com.example.demo.business;

import com.example.demo.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Override
    <S extends Usuario> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Usuario> S save(S entity);

    @Override
    Optional<Usuario> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Usuario entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends Usuario> entities);

    @Override
    void deleteAll();
}
