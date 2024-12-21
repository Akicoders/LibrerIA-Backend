package com.example.demo.service;

import com.example.demo.business.GeneroRepository;
import com.example.demo.model.Genero;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public ImmutableList<Genero> ObtenerGeneros() {
        return ImmutableList.copyOf(generoRepository.findAll());
    }

    @Override
    public void agregarGenero(Genero genero) {
        generoRepository.save(genero);
    }

    @Override
    public void actualizarGenero(Genero genero) {
        generoRepository.save(genero);
    }

    @Override
    public Genero obtenerPorId(int id) {
        return generoRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean existeGeneroPorId(Integer id) {
        return generoRepository.existsById(id);
    }

    @Override
    public Long contarGenero() {
        return generoRepository.count();
    }

    @Override
    public void eliminarPorId(int id) {
        generoRepository.deleteById(id);
    }

    @Override
    public void eliminarGenero(Genero genero) {
        generoRepository.delete(genero);
    }
}
