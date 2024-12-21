package com.example.demo.business;

import com.example.demo.model.Administrador;
import com.example.demo.model.Resena;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    @Override
    <S extends Administrador> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Administrador> S save(S entity);

    @Override
    Optional<Administrador> findById(Integer integer);

    @Override
    long count();

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Administrador entity);
}
