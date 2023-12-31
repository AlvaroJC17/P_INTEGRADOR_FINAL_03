package com.javabootcamp.proyecto_integrador3.Servicios;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javabootcamp.proyecto_integrador3.Entidades.Usuario;
import com.javabootcamp.proyecto_integrador3.Enums.Rol;
import com.javabootcamp.proyecto_integrador3.MiExcepcion.MiExcepcion;
import com.javabootcamp.proyecto_integrador3.Repositorios.RepositorioUsuario;

@Service
public class ServicioUsuario /* implements UserDetailsService*/ {

	@Autowired
	public RepositorioUsuario repositorioUsuario; //Se instancia el repositorio usuarios para acceder a todos los metodos de JPA
	
	
	
	//Crear y guardar un usuario en la base de datos, despues de verificar sus credenciales
	@Transactional
    public void guardarUsuario(String email, String password, String password2, String dni) throws MiExcepcion {

        verificarEmail(email); // primero verificamos que la contraseña cumpla las condiciones
        verificarPassword(password, password2); // Vericamos las condiciones del mail, no este vacio o que ya este registrado
        verificarDni(dni); // verificamos el dni
        
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setDni(dni);
       // usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setPassword(password);
        usuario.setActivo(TRUE); // el valor true se importa de un clase de java por eso no se crea un variable boolean
        usuario.setRol(Rol.PACIENTE); // este valor de rol.paciente se importa de los enum
        repositorioUsuario.save(usuario);
    }
	
	 @Transactional // se usa este marcador para hacer rollback, se usa en los metodos que impactan directo en la base de datos
	    public void modificarUsuario(String email, String password, String password2, String id, String dni) throws MiExcepcion {
	        verificarPassword(password, password2);

	        Optional<Usuario> presente = repositorioUsuario.findById(id); //Opcional puede devolver un valor o no, nos permite acceder al metodo isPresent

	        if (presente.isPresent()) {
	            Usuario usuario = presente.get(); // si pasa el condicional, esta linea guarda a ese usuario en la variable usuario
	            usuario.setPassword(password);
	            repositorioUsuario.save(usuario); // graba el asuario modificado(password) en la base de datos,  el metodo save lo aporta la clase JPA de RepositorioUsuario
	        }

	    }
	 
	 @Transactional
	    public void modificarRol(Rol rol, String id) { //recibe de paramtros el rol a cambiar y el id del usuario
	        Usuario usuario = new Usuario();
	        Optional<Usuario> presente = repositorioUsuario.findById(id); // buscamos al usuario por el id con un opcional

	        if (presente.isPresent()) {
	            usuario = presente.get(); //si cumple el condicional se guarda en la variable usuario
	            usuario.setRol(rol); // se setea el nuevo rol al usuario
	            repositorioUsuario.save(usuario); // se guarda en la base de datos, el metodo save lo aporta la clase JPA de RepositorioUsuario
	        }
	    }
	 
	 //Metodo para eliminar usuarios
	 @Transactional
	    public void eliminarUsuario(String id) {
	        Usuario usuario = new Usuario();
	        Optional<Usuario> presente = repositorioUsuario.findById(id);

	        if (presente.isPresent()) {
	            usuario = presente.get();
	            repositorioUsuario.delete(usuario); //se guarda en la base de datos, el metodo delete lo aporta la clase JPA de RepositorioUsuario
	        }
	    }

	 // Metodo para colocar a un usuario la etiqueta de inactivo
	 @Transactional
	    public void darBaja(String id) {
	        Usuario usuario = new Usuario();
	        Optional<Usuario> presente = repositorioUsuario.findById(id);

	        if (presente.isPresent()) {
	            usuario = presente.get();
	            usuario.setActivo(FALSE); //El valor false se importa de un clase propia de java
	            repositorioUsuario.save(usuario);
	        }
	    }
	 
	 //Metodo para cambiar a usuarios que estaban inactivos a activos
	 @Transactional
	    public void darAlta(String id) {
	        Usuario usuario = new Usuario();

	        Optional<Usuario> presente = repositorioUsuario.findById(id);

	        if (presente.isPresent()) {
	            usuario = presente.get();
	            usuario.setActivo(TRUE);
	            repositorioUsuario.save(usuario);
	        }
	    }
	 
	 public Usuario getOne(String id) { //no se para que se usa
	        return repositorioUsuario.getById(id);
	    }

	    @Transactional(readOnly = true) // Ademas del el transactional el readonly indica que solo es un metodo de lectura, y que este metodo no puede hacer modificaciones en la base de datos
	    public List<Usuario> listarUsuarios() {
	    	List<Usuario> usuarios = new ArrayList();
	    	usuarios = repositorioUsuario.findAll();
	    	return usuarios; //devuelve un array list con todos los usuarios de la base de datos
	    }
	    
	    @Transactional
	    public void crearUsuario(String dni, String password, Rol rol, String email) throws MiExcepcion {
	        Usuario presente = repositorioUsuario.buscarPorDni(dni); // Se busca por mail porque el usuario no tiene dni registrado en la clase
	        if (presente == null) {
	            Usuario usuario = new Usuario();
	            usuario.setEmail(email);
	            usuario.setDni(dni);
	           // usuario.setPassword(new BCryptPasswordEncoder().encode(password));
	            usuario.setPassword(password);
	            usuario.setActivo(TRUE);
	            usuario.setRol(rol);
	            repositorioUsuario.save(usuario);
	        }
	    }



	 //Metodo para validar el mail que se esta registrando
	public void verificarEmail(String email) throws MiExcepcion {
		if (email.isEmpty()) {
			throw new MiExcepcion("El email no puede estar vacío");
		}
		if (repositorioUsuario.buscarPorEmailOptional(email).isPresent()) { // invoca el opcional isPresent de
			throw new MiExcepcion("El email ya está registrado");			// verificar el mail que esta ingresando el													// repositorioUsuario y lo que hace
		}																	// usuario este presente en la base de datos
	}																	
			
		//Metodo para validar el pass que se esta registrando
	public boolean verificarPassword(String password, String password2) throws MiExcepcion {
		if (password.isEmpty()) {
			throw new MiExcepcion("La constraseña no puede estar vacía");
		}
		if (password.trim().isEmpty()) { // valida que la contraseña no incluya espacios en blanco
			throw new MiExcepcion("La contraseña no puede estar vacía");
		}
		if (!password.equals(password2)) {
			throw new MiExcepcion("Las contraseñas no coinciden");
		}
		return true;
	}
	
	 public void verificarDni(String dni) throws MiExcepcion{
	    	if (repositorioUsuario.buscarPorDniOptional(dni).isPresent()) {
				throw new MiExcepcion("Erro, el dni ya se encuentra registrado");
			}
	    	
	    	if (dni == null || dni.isEmpty() || dni.trim().isEmpty()) {
	    		throw new MiExcepcion("Error, el dni no puede estar vacio");
			}
	    	
	    }

	/*@Override //Autentica un usuario para spring security y verifica que en cada solicitud de ese usuario todavia tenga los permisos de acceso
	public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {

		 Usuario usuario = repositorioUsuario.buscarPorDni(dni);

	        if (usuario == null) {
	            throw new UsernameNotFoundException("ERROR: Usuario no encontrado");
	        }

	        if (!usuario.getActivo()) {
	            throw new UsernameNotFoundException("ERROR: Usuario inactivo");
	        }

	        List<GrantedAuthority> permisos = new ArrayList<>();
	        GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
	        permisos.add(p);
	        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	        HttpSession session = attr.getRequest().getSession(true);
	        session.setAttribute("usuariosession", usuario);
	        return new User(usuario.getDni(), usuario.getPassword(), permisos);
	    }*/

}
