package com.javabootcamp.proyecto_integrador3.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javabootcamp.proyecto_integrador3.Entidades.Profesional;
import com.javabootcamp.proyecto_integrador3.Enums.Provincias;
import com.javabootcamp.proyecto_integrador3.Enums.Tratamiento;



@Repository
public interface RepositorioProfesional extends JpaRepository<Profesional, String> {

    @Query("SELECT p FROM Profesional p")
    public List<Profesional> listarProfesionales();

    @Query("SELECT p FROM Profesional p WHERE p.tratamiento = :tratamiento")
    public List<Profesional> buscarPorTratamiento(@Param("tratamiento") Tratamiento tratamiento);

    @Query("SELECT p FROM Profesional p WHERE p.provincia = :provincia")
    public List<Profesional> buscarPorLocalidad(@Param("provincia") Provincias provincia);

    @Query("SELECT DISTINCT(p.provincia) FROM Profesional p")
    public List<String> listarProvincias();

   @Query("SELECT DISTINCT(p.tratamiento) FROM Profesional p WHERE p.provincia = :provincia")
    public List<String> listarTratamientoPorProvincia(@Param("provincia") Provincias provincia);

   @Query("SELECT DISTINCT(p) FROM Profesional p WHERE (p.provincia = :provincia AND p.tratamiento = :tratamiento AND p.disponibilidad.dias IS NOT NULL)")
    public List<Profesional> listarProfesionalPorTratamientoPorProvincia(@Param("provincia") Provincias provincia,
            @Param("tratamiento") Tratamiento tratamiento);

    @Query("SELECT p FROM Profesional p WHERE p.nombre LIKE %:nombre%")
    public List<Profesional> buscarPorNombre(@Param("nombre") String nombre);   
    
    @Query("SELECT p FROM Profesional p WHERE p.id = :id")
    public Profesional buscarPorId(@Param("id") String id);
    
    @Query("SELECT p FROM Profesional p WHERE p.dni = :dni")
    public Profesional buscarPorDni(@Param("dni") String dni);

    @Query("SELECT COUNT(p) FROM Profesional p")
    public Integer contarProfesionales();


}
