package servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entidades.Disponibilidad;
import jakarta.transaction.Transactional;
import repositorios.RepositorioDisponibilidad;

@Service
public class ServicioDisponibilidad {
	
	@Autowired //Llamamos al repositorio y sus metodos
	public RepositorioDisponibilidad repositorioDisponibilidad;
	
	
	@Transactional
	 public Disponibilidad guardar(Disponibilidad disponibilidad){
	        return repositorioDisponibilidad.save(disponibilidad);
	    }
	
	 @Transactional
	 public Disponibilidad modificar(String id, Disponibilidad disponibilidad){
	        Optional<Disponibilidad> optional = repositorioDisponibilidad.findById(id);
	        if (optional.isPresent()){
	            Disponibilidad disponibilidad2 = optional.get();
	            disponibilidad2.setDias(disponibilidad.getDias());;
	            disponibilidad2.setEntrada(disponibilidad.getEntrada());
	            disponibilidad2.setInicioDescanso(disponibilidad.getInicioDescanso());
	            disponibilidad2.setFinDescanso(disponibilidad.getFinDescanso());
	           // disponibilidad2.setFinDescanso(disponibilidad.getFinDescanso());
	            return repositorioDisponibilidad.save(disponibilidad2);
	        }
	        return null;
	    }
	

}
