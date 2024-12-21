package com.example.demo.service;

import com.example.demo.business.LibroRepository;
import com.example.demo.model.Libro;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public ImmutableList<Libro> ObtenerLibros() {
        return ImmutableList.copyOf(libroRepository.findAll());
    }

    @Override
    public Libro obtenerPorId(int id) {
     return libroRepository.findById(id).orElse(null);
   }

    @Override
    public Boolean existeLibroPorId(Integer id) {
        return libroRepository.existsById(id);
    }

    @Override
    public Long contarLibro() {
        return libroRepository.count();
    }

    @Override
    public void agregarLibro(Libro libro) {
    libroRepository.save(libro);
    }

    @Override
    public void eliminarPorId(int id) {
        libroRepository.deleteById(id);
    }

    @Override
    public void eliminarLibro(Libro libro) {
    libroRepository.delete(libro);
    }

    @Override
    public void actualizarLibro(Libro libro) {
    libroRepository.save(libro);
    }


}
