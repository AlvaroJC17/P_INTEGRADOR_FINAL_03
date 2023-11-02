package com.javabootcamp.proyecto_integrador3.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javabootcamp.proyecto_integrador3.Entidades.Cliente;
import com.javabootcamp.proyecto_integrador3.Entidades.Usuario;
import com.javabootcamp.proyecto_integrador3.Enums.Sexo;

import jakarta.servlet.http.HttpSession;
import servicios.ServicioProfesional;

@Controller
@RequestMapping("/perfil")
public class ControladorPersona {

	 @Autowired
	    private ServicioProfesional servicioProfesional;
	   
	    @GetMapping("/datos")
	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE', 'ROLE_PROFESIONAL')")
	    public String vistaDatosPerfil(HttpSession session, ModelMap modelo) {

	        modelo.put("sexos", Sexo.values());
	        String tipo = "";

	        if (session.getAttribute("usuariosession") instanceof Usuario) {
	            tipo = "Usuario";
	            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
	            modelo.put("usuario", usuario);
	        }
	        if (session.getAttribute("usuariosession") instanceof Cliente) {
	            tipo = "Cliente";
	            Cliente cliente = (Cliente) session.getAttribute("usuariosession");
	            modelo.put("cliente", cliente);
	        }
	        modelo.put("tipo", tipo);
	        return "form.html";
	    }

	   /* @GetMapping("/imagen")
	    public ResponseEntity<byte[]> imagenUsuario(HttpSession session) {
	        Persona persona = (Persona) session.getAttribute("usuariosession");
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);
	        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
	    }

	    @GetMapping("/imagen/profesional/{id}")
	    public ResponseEntity<byte[]> imagenProfesional(@PathVariable String id, HttpSession session) {
	        Profesional profesional = profesionalService.buscarProfesional(id);
	        byte[] imagen = profesional.getImagen().getContenido();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);
	        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
	    }*/
}
