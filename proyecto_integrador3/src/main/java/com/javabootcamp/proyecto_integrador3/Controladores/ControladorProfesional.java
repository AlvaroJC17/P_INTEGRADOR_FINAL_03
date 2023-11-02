package com.javabootcamp.proyecto_integrador3.Controladores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javabootcamp.proyecto_integrador3.Entidades.Disponibilidad;
import com.javabootcamp.proyecto_integrador3.Entidades.Profesional;
import com.javabootcamp.proyecto_integrador3.Entidades.Usuario;
import com.javabootcamp.proyecto_integrador3.Enums.DiasDeLaSemana;
import com.javabootcamp.proyecto_integrador3.Enums.Provincias;
import com.javabootcamp.proyecto_integrador3.Enums.Sexo;
import com.javabootcamp.proyecto_integrador3.Enums.Tratamiento;
import com.javabootcamp.proyecto_integrador3.MiExcepcion.MiExcepcion;

import jakarta.servlet.http.HttpSession;
import repositorios.RepositorioProfesional;
import repositorios.RepositorioUsuario;
import servicios.ServicioProfesional;
import servicios.ServicioUsuario;

@Controller
@RequestMapping("/profesional")
//@PreAuthorize("hasAnyRole('ROLE_PROFESIONAL')")
public class ControladorProfesional {

	 @Autowired
	    private ServicioProfesional servicioProfesional;

	    @Autowired
	    private ServicioUsuario servicioUsuario;
	    
	    @Autowired
	    private RepositorioUsuario repositorioUsario;
	    
	    @Autowired
	    private RepositorioProfesional repositorioProfesional;


	    @GetMapping("/perfil")
	    public String vistaDatosPerfil(ModelMap modelo, HttpSession session) {
	        modelo.put("sexos", Sexo.values());
	        modelo.put("especialidades", Tratamiento.values());
	        modelo.put("provincias", Provincias.values());
	        modelo.put("dias_laborales", DiasDeLaSemana.values());
	        String tipo = "";

	        if (session.getAttribute("usuariosession") instanceof Usuario) {
	            tipo = "Usuario";
	            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
	            modelo.put("usuario", usuario);
	        }
	        if (session.getAttribute("usuariosession") instanceof Profesional) {
	            tipo = "Profesional";
	            Profesional profesional = (Profesional) session.getAttribute("usuariosession");
	            modelo.put("profesional", profesional);
	        }
	        modelo.put("tipo", tipo);
	        return "profesional_form.html";
	    }

	    @GetMapping("/turno")
	    public String reservarTurno(ModelMap modelo) throws MiExcepcion {

	        List<Profesional> profesionales = servicioProfesional.listarProfesionales();
	        modelo.addAttribute("profesionales", profesionales);

	        modelo.put("especialidades", Tratamiento.values());

	        return "turno.html";
	    }

	    @PostMapping("/registro")
	    public String registroProfesional(String nombre, String apellido,
	            Sexo sexo, String fechaNacimiento, String domicilio, String dni,
	            Provincias provincia, String matricula, Tratamiento tratamientos,
	            String[] diasLaborales, Integer entrada, Integer inicio_descanso, Integer fin_descanso, Integer salida,
	            ModelMap modelo, HttpSession session, String email) throws Exception {

	        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	        Date dataFormateada = formato.parse(fechaNacimiento);

	        Disponibilidad disponibilidad = new Disponibilidad();
	        disponibilidad.setDias(diasLaborales);
	        disponibilidad.setEntrada(entrada);
	        disponibilidad.setInicioDescanso(inicio_descanso);
	        disponibilidad.setFinDescanso(fin_descanso);
	        disponibilidad.setSalida(salida);

	        if (session.getAttribute("usuariosession") instanceof Profesional) {
	            Profesional profesional = (Profesional) session.getAttribute("usuariosession");
	            servicioProfesional.modificarProfesional(profesional, nombre, apellido, sexo, dataFormateada, domicilio,
	                    dni, provincia, matricula, tratamientos, email, disponibilidad);
	           // servicioUsuario.loadUserByUsername(profesional.getDni());
	            repositorioProfesional.buscarPorDni(dni);
	            
	        } else if (session.getAttribute("usuariosession") instanceof Usuario) {
	            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
	            servicioProfesional.guardarProfesional(nombre, apellido, sexo, dataFormateada, domicilio, dni,
	                    provincia, matricula, tratamientos, disponibilidad, usuario);
	            //servicioUsuario.loadUserByUsername(usuario.getDni());
	            repositorioUsario.findById(dni);
	        }
	        return "redirect:/";
	    }
	
}
