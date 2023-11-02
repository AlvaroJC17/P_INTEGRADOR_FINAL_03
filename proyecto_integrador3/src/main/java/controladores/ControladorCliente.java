package controladores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entidades.Cliente;
import entidades.Consulta;
import entidades.Usuario;
import enums.Sexo;
import jakarta.servlet.http.HttpSession;
import repositorios.RepositorioUsuario;
import servicios.ServicioCliente;
import servicios.ServicioConsulta;
import servicios.ServicioUsuario;

@Controller
@RequestMapping("/paciente")
public class ControladorCliente {
	
	    @Autowired
	    private ServicioCliente servicioCliente;
	    
	    @Autowired
	    private ServicioUsuario servicioUsuario;
	    
	    @Autowired
	    private RepositorioUsuario repositorioUsuario;
	    
	    @Autowired
	    private ServicioConsulta servicioConsulta;
	    

	    @PostMapping("/registro")
	   // @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
	    public String crearPaciente(@RequestParam String nombre, @RequestParam String apellido,
	            @RequestParam String domicilio, @RequestParam String dni, @RequestParam Sexo sexo,
	            @RequestParam String fechaNacimiento, Integer edad, Integer telefono, HttpSession session) throws Exception {

	        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	        Date dataFormateada = formato.parse(fechaNacimiento);

	      
	        if (session.getAttribute("usuariosession") instanceof Cliente) {
	        	Cliente cliente = (Cliente) session.getAttribute("usuariosession");
	        	servicioCliente.modificarCliente(cliente, nombre, apellido, sexo, dataFormateada, domicilio, dni, edad, telefono);
	        	//servicioUsuario.loadUserByUsername(cliente.getDni());
	        	repositorioUsuario.buscarPorDni(dni);
	        	

	        } else if (session.getAttribute("usuariosession") instanceof Usuario) {
	            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
	            servicioCliente.guardarCliente(nombre, apellido, sexo, dataFormateada, domicilio, dni, edad, telefono, usuario);
	            repositorioUsuario.buscarPorDni(usuario.getDni());
	        
	        }
	        return "redirect:/";
	    }

	    @GetMapping("/consulta")
	  //  @PreAuthorize("hasRole('ROLE_PACIENTE')")
	    public String mostrarConsulta(Model modelo, HttpSession session) {

	            Cliente cliente = (Cliente) session.getAttribute("usuariosession");
	            
	            List<Consulta> consulta = servicioConsulta.listarConsulta(cliente.getId());
	          
	            modelo.addAttribute("consulta", consulta);
	        
	        return "consulta_paciente.html";
	    }

}