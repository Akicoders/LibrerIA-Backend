package com.example.demo.service;

import com.example.demo.business.AutorRepository;
import com.example.demo.model.Autor;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl implements AutorService {
    @Autowired
    private AutorRepository autorRepository;


    @Override
    public ImmutableList<Autor> ObtenerAutores() {
        return ImmutableList.copyOf(autorRepository.findAll());
    }

    @Override
    public void agregarAutor(Autor autor) {
        autorRepository.save(autor);
    }

    @Override
    public void actualizarAutor(Autor autor) {
        autorRepository.save(autor);
    }

    @Override
    public Autor obtenerPorId(int id) {
        return autorRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean existeAutorPorId(Integer id) {
        return autorRepository.existsById(id);
    }

    @Override
    public Long contarAutor() {
        return autorRepository.count();
    }

    @Override
    public void eliminarPorId(int id) {
        autorRepository.deleteById(id);
    }

    @Override
    public void eliminarAutor(Autor autor) {
        autorRepository.delete(autor);
    }
}
