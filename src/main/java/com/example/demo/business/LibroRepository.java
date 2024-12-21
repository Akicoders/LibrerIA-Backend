package com.example.demo.business;

import com.example.demo.model.Libro;
import com.example.demo.model.Resena;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {

    @Override
    <S extends Libro> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Libro> S save(S entity);

    @Override
    Optional<Libro> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Libro entity);


}
