package com.example.demo.business;

import com.example.demo.model.Resena;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResenaRepository extends JpaRepository<Resena,Integer> {

    @Override
    <S extends Resena> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Resena> S save(S entity);

    @Override
    Optional<Resena> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    long count();

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Resena entity);

    @Override
    void deleteAllById(Iterable<? extends Integer> integers);

    @Override
    void deleteAll(Iterable<? extends Resena> entities);
}
