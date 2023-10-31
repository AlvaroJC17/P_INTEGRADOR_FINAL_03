package repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import entidades.Disponibilidad;

@Repository
public interface RepositorioDisponibilidad extends JpaRepository<Disponibilidad, String>{

	
	public List<Disponibilidad> findAll();
	
	@Query("SELECT d FROM Consulta d WHERE d.disponibilidad.id = :id")
	public List<Disponibilidad> buscarDisponibilidadPorId(@Param("id") String id);
	
}
