package com.example.demo.business;

import com.example.demo.model.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Rol,Long> {
   List<Rol> findRolsByRolIn(List<String> roleNames);
}
