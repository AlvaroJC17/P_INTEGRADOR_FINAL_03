package servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import static java.lang.Boolean.FALSE;
import Excepciones.MiExcepcion;
import entidades.Disponibilidad;
import entidades.Profesional;
import entidades.Usuario;
import enums.Provincias;
import enums.Sexo;
import enums.Tratamiento;
import jakarta.transaction.Transactional;
import repositorios.RepositorioProfesional;

public class ServicioProfesional {

	@Autowired
	private RepositorioProfesional repositorioProfesional;

	@Autowired
	private ServicioUsuario servicioUsuario;


	@Autowired
	private ServicioDisponibilidad servicioDisponibilidad;
	
	//Meotod para pasar de un usuario a profesional, por eso parte de los datos los tomatos de la clase usuario
	 @Transactional
	    public void guardarProfesional(String nombre, String apellido,
	            Sexo sexo, Date fechaNacimiento, String domicilio, String dni,
	            Provincias provincia, String matricula,
	            Tratamiento tratamientos, Disponibilidad disponibilidad, Usuario usuario) throws MiExcepcion {

	        verificarProfesional(nombre, apellido, domicilio, matricula); // Verificamos le registro del profesional a traves de este metodo

	        Profesional profesional = new Profesional();

	        profesional.setId(usuario.getId()); //Traemos los valores de usuario de la clase usuario y se los asignamos a ese profesional
	        profesional.setDni(usuario.getDni());
	        profesional.setEmail(usuario.getEmail());
	        profesional.setPassword(usuario.getPassword());
	        profesional.setRol(usuario.getRol());
	        profesional.setActivo(usuario.getActivo());
	        profesional.setNombre(nombre);
	        profesional.setApellido(apellido);
	        profesional.setDomicilio(domicilio);
	        profesional.setFechaNacimiento(fechaNacimiento);
	        profesional.setMatricula(matricula);
	        profesional.setSexo(sexo);
	        profesional.setProvincia(provincia);
	        profesional.setTratamiento(tratamientos);
	        profesional.setDisponibilidad(servicioDisponibilidad.guardar(disponibilidad));
	        repositorioProfesional.save(profesional); // Guarda el nuevo profesional en la base de datos

	        servicioUsuario.eliminarUsuario(usuario.getId()); // Como este usuario ya es profesional, entonces se elimina de la base de datos
	    }
	 
	 @Transactional
	    public void modificarProfesional(Profesional profesional, String nombre, String apellido,
	            Sexo sexo, Date fechaNacimiento, String domicilio, String dni,
	            Provincias provincia, String matricula, Tratamiento tratamientos, Disponibilidad disponibilidad)
	            throws MiExcepcion {

	        Optional<Profesional> respuesta = repositorioProfesional.findById(profesional.getId());

	        if (respuesta.isPresent()) {
	            Profesional nuevo_profesional = respuesta.get();

	            nuevo_profesional.setNombre(nombre);
	            nuevo_profesional.setApellido(apellido);
	            nuevo_profesional.setSexo(sexo);
	            nuevo_profesional.setDomicilio(domicilio);
	            nuevo_profesional.setFechaNacimiento(fechaNacimiento);
	            nuevo_profesional.setDni(dni);
	            nuevo_profesional.setDisponibilidad(disponibilidad);
	            nuevo_profesional.setTratamiento(tratamientos);
	            nuevo_profesional.setMatricula(matricula);
	            nuevo_profesional.setProvincia(provincia);
	            String idDisponibilidad = profesional.getDisponibilidad().getId();
	            nuevo_profesional.setDisponibilidad(servicioDisponibilidad.modificar(idDisponibilidad, disponibilidad));
	            repositorioProfesional.save(nuevo_profesional);
	        }
	    }
	 
	 
	 @Transactional
	    public void borrarProfesional(Profesional profesional) {

		 repositorioProfesional.delete(profesional);

	    }
	 
	 @Transactional()
	    public List<Profesional> listarProfesionales() {

	        List<Profesional> profesionales = new ArrayList();

	        profesionales = repositorioProfesional.findAll();

	        return profesionales;

	    }
	 
	 @Transactional()
	    public List<Profesional> listarPorNombre(String nombre) {
	        List<Profesional> profesionales = new ArrayList();

	        profesionales = repositorioProfesional.buscarPorNombre(nombre);

	        return profesionales;
	    }

	  public List<Profesional> listarPorEspecialidad(Tratamiento tratamientos) {
	        List<Profesional> profesionales = new ArrayList();
	        profesionales = repositorioProfesional.buscarPorEspecialidad(tratamientos);
	        return profesionales;
	    }

	    public List<Profesional> listarPorLocalidad(Provincias provincia) {
	        List<Profesional> profesionales = new ArrayList();
	        profesionales = repositorioProfesional.buscarPorLocalidad(provincia);
	        return profesionales;
	    }
	    
	    @Transactional
	    public void eliminarProfesional(String id) {

	        Optional<Profesional> presente = repositorioProfesional.findById(id);
	        if (presente.isPresent()) {

	            Profesional profesional = presente.get();
	            profesional.setActivo(FALSE);
	            repositorioProfesional.save(profesional);

	        }

	    }
	    
	    @Transactional
	    public Profesional buscarProfesional(String id){
	        Profesional profesional = repositorioProfesional.buscarPorId(id);
	        return profesional;
	    }

	        @Transactional()
	        public List<String> listarProvincias() {
	            List<String> provincias = new ArrayList();
	            provincias = repositorioProfesional.listarProvincias();
	            return provincias;
	        }

	      /*  @Transactional()
	        public List<String> listarEspecialidadesPorProvincia(String provincia) {
	            List<String> especialidades = new ArrayList();
	            Provincias prov = Provincias.valueOf(provincia);
	            especialidades = repositorioProfesional.listarEspecialidadesPorProvincia(prov);
	            return especialidades;
	        }*/

	      /*  @Transactional()
	        public List<Profesional> listarProfesionalPorEspecialidadesPorProvincia(String provincia, String tratamientos) {
	            List<Profesional> profesionales = new ArrayList();
	            Provincias prov = Provincias.valueOf(provincia);
	            Tratamiento tra = Tratamiento.valueOf(tratamientos);
	            profesionales = repositorioProfesional.listarProfesionalPorEspecialidadesPorProvincia(prov, tra);
	            return profesionales;
	        }*/

	        public Profesional getOne(String id) {
	            return repositorioProfesional.getById(id);
	        }

	        @Transactional
	        public void crearProfesional(Profesional profesional){
	        	repositorioProfesional.save(profesional);
	        }

	        @Transactional()
	        public Integer contarProfesionales(){
	            return repositorioProfesional.contarProfesionales();
	        }

	 
	 public void verificarProfesional(String nombre, String apellido, String domicilio, String matricula)
	            throws MiExcepcion {

	        if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()) {

	            throw new MiExcepcion("Error, el nombre no puede estar vacio");

	        }
	        if (apellido == null || apellido.isEmpty() || apellido.trim().isEmpty()) {

	            throw new MiExcepcion("Error, el apellido no puede estar vacio");

	        }
	        if (domicilio == null || domicilio.isEmpty() || domicilio.trim().isEmpty()) {

	            throw new MiExcepcion("Error, el domicilio no puede estar vacio");

	        }
	        if (matricula == null || matricula.isEmpty() || matricula.trim().isEmpty()) {

	            throw new MiExcepcion("Error, la matrícula no puede estar vacía o ser un valor nulo");

	        }

	    }


}
