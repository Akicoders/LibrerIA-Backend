package com.example.demo.service;

import com.example.demo.business.AdministradorRepository;
import com.example.demo.model.Administrador;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiceImpl implements AdministradorService {
   @Autowired
   AdministradorRepository administradorRepository;

   @Override
   public ImmutableList<Administrador> ObtenerAdministradores() {
      return ImmutableList.copyOf(administradorRepository.findAll());
   }

   @Override
   public void agregarAdministrador(Administrador administrador) {
      administradorRepository.save(administrador);
   }

   @Override
   public void actualizarAdministrador(Administrador administrador) {
      administradorRepository.save(administrador);
   }

   @Override
   public Administrador obtenerPorId(int id) {
      return administradorRepository.findById(id).orElse(null);
   }

   @Override
   public Boolean existeAdministradorPorId(Integer id) {
      return administradorRepository.existsById(id);
   }

   @Override
   public Long contarAdministrador() {
      return administradorRepository.count();
   }

   @Override
   public void eliminarPorId(int id) {
      administradorRepository.deleteById(id);
   }

   @Override
   public void eliminarAdministrador(Administrador administrador) {
      administradorRepository.delete(administrador);
   }
}
