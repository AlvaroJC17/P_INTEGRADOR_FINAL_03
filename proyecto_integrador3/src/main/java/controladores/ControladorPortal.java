package controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Excepciones.MiExcepcion;
import entidades.Cliente;
import entidades.Profesional;
import enums.Tratamiento;
import jakarta.servlet.http.HttpSession;
import servicios.ServicioProfesional;
import servicios.ServicioUsuario;

@Controller
@RequestMapping("/")
public class ControladorPortal {
	
	    @Autowired
	    private ServicioUsuario servicioUsuario;
	    @Autowired
	    private ServicioProfesional servicioProfesional;

	    @GetMapping("/login")
	    public String ingresar(@RequestParam(required = false) String error, ModelMap modelo) {
	        if (error != null) {
	            modelo.put("error", "email y contraseña no coinciden");
	        }
	        return "index.html";
	    }

	    @GetMapping("/especialidades")
	    public String listaEspecialidades(ModelMap modelo) throws MiExcepcion {

	        List<Profesional> profesionales = servicioProfesional.listarProfesionales();
	        modelo.addAttribute("profesionales", profesionales);

	        //modelo.put("exito", "La lista de profesionales se muestra a continuación");
	        modelo.put("especialidades", Tratamiento.values());

	        return "especialidades.html";

	    }

	@GetMapping("/especialidad/busqueda")
	public String listarProfesionalPorNombre(String nombre, ModelMap modelo){

	    List<Profesional> profesional = servicioProfesional.listarPorNombre(nombre);
	    modelo.addAttribute("profesionales", profesional);
	    modelo.put("especialidades", Tratamiento.values());

	    return "especialidades.html";
	}

	@GetMapping("/especialidad/profesionales/{especialidad}")
	public String listarPorEspecialidad(@PathVariable Tratamiento tratamiento, ModelMap modelo){
	    
	    List<Profesional> profesional = servicioProfesional.listarPorEspecialidad(tratamiento);
	    modelo.addAttribute("profesionales", profesional);
	    modelo.put("especialidades", Tratamiento.values());

	    return "especialidades.html";
	}

	    @GetMapping("/")
	    public String inicio(HttpSession session, ModelMap modelo) {

	        String tipo = "";

	        if (session.getAttribute("usuariosession") instanceof Profesional) {
	            Profesional profesional = (Profesional) session.getAttribute("usuariosession");
	            modelo.put("usuario", profesional);
	            tipo = "Profesional";
	        }
	        if (session.getAttribute("usuariosession") instanceof Cliente) {
	            Cliente cliente = (Cliente) session.getAttribute("usuariosession");
	            modelo.put("usuario", cliente);
	            tipo = "Cliente";
	        }

	        modelo.put("tipo", tipo);
	        return "index.html";
	    }

	    @PostMapping("/registro")
	    public String registroUsuario(@RequestParam String email, @RequestParam String password,
	            String password2, @RequestParam String dni, ModelMap modelo) throws MiExcepcion {

	        try {

	        	servicioUsuario.guardarUsuario(email, password, password2, dni);

	            modelo.put("exito", "Usuario registrado correctamente!");

	            return "index.html";
	        } catch (MiExcepcion e) {
	            System.out.println(e.getMessage());
	            modelo.put("error", e.getMessage());
	            modelo.put("email", email);
	            modelo.put("dni", dni);
	            return "index.html";
	        }

	    }
	

}
