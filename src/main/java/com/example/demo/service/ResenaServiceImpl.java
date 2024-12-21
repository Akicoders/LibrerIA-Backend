package com.example.demo.service;

import com.example.demo.business.ResenaRepository;
import com.example.demo.model.Resena;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResenaServiceImpl implements ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    @Override
    public ImmutableList<Resena> ObtenerResenas() {
        return ImmutableList.copyOf(resenaRepository.findAll());
    }

    @Override
    public void agregarResena(Resena resena) {
        resenaRepository.save(resena);
    }

    @Override
    public void actualizarResena(Resena resena) {
        resenaRepository.save(resena);
    }

    @Override
    public Resena obtenerPorId(int id) {
        return resenaRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean existeResenaPorId(Integer id) {
        return resenaRepository.existsById(id);
    }

    @Override
    public Long contarResena() {
        return resenaRepository.count();
    }

    @Override
    public void eliminarPorId(int id) {
        resenaRepository.deleteById(id);
    }

    @Override
    public void eliminarResena(Resena resena) {
        resenaRepository.delete(resena);
    }
}
