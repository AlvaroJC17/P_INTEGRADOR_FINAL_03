package com.javabootcamp.proyecto_integrador3.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javabootcamp.proyecto_integrador3.Entidades.Cliente;


@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, String>{
    @Query("SELECT COUNT(cl) FROM Cliente cl")
    public Integer contarClientes();
}
