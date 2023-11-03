package com.javabootcamp.proyecto_integrador3.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javabootcamp.proyecto_integrador3.Entidades.Disponibilidad;

@Repository
public interface RepositorioDisponibilidad extends JpaRepository<Disponibilidad, String>{

	
	//public List<Disponibilidad> findAll(); // no hace falta colocarlo aqui, puedo invocar este metodo directamente en el controlador
	
	//@Query("SELECT d FROM Consulta d WHERE d.disponibilidad.id = :id")
	//public List<Disponibilidad> buscarDisponibilidadPorId(@Param("id") String id);

}
