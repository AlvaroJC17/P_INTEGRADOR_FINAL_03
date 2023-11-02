package servicios;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Excepciones.MiExcepcion;
import entidades.Cliente;
import entidades.Usuario;
import enums.Sexo;
import repositorios.RepositorioCliente;
import repositorios.RepositorioUsuario;

@Service
public class ServicioCliente {
	
	@Autowired
	private RepositorioCliente repositorioCliente;
	
	@Autowired
	private RepositorioUsuario repositorioUsuario;
	
	
	@Transactional
    public void guardarCliente(String nombre, String apellido,
            Sexo sexo, Date fechaNacimiento, String domicilio, String dni, Integer edad, Integer telefono,
            Usuario usuario) throws MiExcepcion {

        verificarNombre(nombre);
        verificarApellido(apellido);
        verificarDomicilio(domicilio);
        verificarEdad(edad);
        verificarTelefono(telefono);

        Cliente cliente = new Cliente();

        cliente.setId(usuario.getId());
        cliente.setEmail(usuario.getEmail());
        cliente.setPassword(usuario.getPassword());
        cliente.setRol(usuario.getRol());
        cliente.setActivo(usuario.getActivo());
        cliente.setDni(usuario.getDni());
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setEdad(edad);
        cliente.setTelefono(telefono);
        cliente.setDomicilio(domicilio);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setSexo(sexo);
        repositorioCliente.save(cliente);

        borrarUsuario(usuario);
    }
	
	
	 @Transactional(readOnly = true)
	    public Cliente listarUnCliente(String id){
	        return repositorioCliente.getOne(id);
	    }

	    @Transactional
	    public void borrarUsuario(Usuario usuario) {
	    	repositorioUsuario.delete(usuario);;
	    }
	    
	    @Transactional
	    public void modificarCliente(Cliente cliente, String nombre, String apellido,
	            Sexo sexo, Date fechaNacimiento, String domicilio, String dni, Integer edad, Integer telefono) throws MiExcepcion {

	        Optional<Cliente> respuesta = repositorioCliente.findById(cliente.getId());

	        if (respuesta.isPresent()) {
	        	Cliente nuevo_cliente = respuesta.get();
	        	nuevo_cliente.setNombre(nombre);
	        	nuevo_cliente.setApellido(apellido);
	        	nuevo_cliente.setEdad(edad);
	        	nuevo_cliente.setTelefono(telefono);
	        	nuevo_cliente.setSexo(sexo);
	        	nuevo_cliente.setDomicilio(domicilio);
	        	nuevo_cliente.setFechaNacimiento(fechaNacimiento);
	        	nuevo_cliente.setDni(dni);
	        	repositorioCliente.save(nuevo_cliente);
	        }
	    }
	    
	    
	    public Cliente getOne(String id) {
	        return repositorioCliente.getById(id);
	    }

	    @Transactional
	    public void crearCliente(Cliente cliente){
	    	repositorioCliente.save(cliente);
	    }

	    @Transactional(readOnly = true)
	    public Integer contarCliente(){
	        return repositorioCliente.contarClientes();
	    }
	
	
	    
	    //Verificaciones
	 public void verificarNombre(String nombre) throws MiExcepcion {
	        if (nombre == null || nombre.isEmpty() || nombre.trim().isEmpty()) {
	            throw new MiExcepcion("Error, el nombre no puede estar vacio");
	        }

	    }

	    public void verificarApellido(String apellido) throws MiExcepcion {
	        if (apellido == null || apellido.isEmpty() || apellido.trim().isEmpty()) {
	            throw new MiExcepcion("Error, el apellido no puede estar vacio");
	        }
	    }

	    public void verificarDomicilio(String domicilio) throws MiExcepcion {
	        if (domicilio == null || domicilio.isEmpty() || domicilio.trim().isEmpty()) {
	            throw new MiExcepcion("Error, el domicilio no puede estar vacio");
	        }
	    }
	    
	    public void verificarEdad(Integer edad) throws MiExcepcion{
	    	String edad2 = edad.toString();
	    	if (edad2 == null || edad2.isEmpty() || edad2.trim().isEmpty()) {
	    		throw new MiExcepcion("Error, la edad no puede estar vacia");
			}
	    }
	    
	    public void verificarTelefono(Integer telefono) throws MiExcepcion{
	    	String tlf = telefono.toString();
	    	if (tlf == null || tlf.isEmpty() || tlf.trim().isEmpty()) {
	    		throw new MiExcepcion("Error, el numero de telefono no puede estar vacio");
			}
	    }
	    

	    

}
