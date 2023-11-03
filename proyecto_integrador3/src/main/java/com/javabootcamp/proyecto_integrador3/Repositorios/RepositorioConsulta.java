package com.javabootcamp.proyecto_integrador3.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javabootcamp.proyecto_integrador3.Entidades.Cliente;
import com.javabootcamp.proyecto_integrador3.Entidades.Consulta;
import com.javabootcamp.proyecto_integrador3.Entidades.Profesional;

@Repository
public interface RepositorioConsulta extends JpaRepository<Consulta, String> {

	@Query("SELECT c.fecha FROM Consulta c WHERE c.profesional = :profesional GROUP BY c.fecha HAVING COUNT(*) = :nTurnos")
	public List<String> listarConsultasPorProfesionalAgrupadoPorFecha(@Param("profesional") Profesional profesional,
			@Param("nTurnos") Integer nTurnos);

	@Query("SELECT c.horario FROM Consulta c WHERE (c.profesional = :profesional AND c.fecha = :fechaConsulta)")
	public List<Integer> listarHorarioPorProfesionalPorFecha(@Param("profesional") Profesional profesional,
			@Param("fechaConsulta") String fecha);

	@Query("SELECT c FROM Consulta c WHERE c.cliente.id = :id ORDER BY c.fecha ASC")
	public List<Consulta> listarConsulta(@Param("id") String id);

	@Query("SELECT DISTINCT(c.cliente) FROM Consulta c WHERE c.profesional.id = :id")
	public List<Cliente> listarClientesPorProfesional(@Param("id") String id);
	
}
