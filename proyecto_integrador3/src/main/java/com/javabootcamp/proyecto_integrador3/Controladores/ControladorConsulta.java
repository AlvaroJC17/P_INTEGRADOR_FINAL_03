package com.javabootcamp.proyecto_integrador3.Controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javabootcamp.proyecto_integrador3.Entidades.Cliente;
import com.javabootcamp.proyecto_integrador3.Entidades.Consulta;
import com.javabootcamp.proyecto_integrador3.Entidades.Profesional;
import com.javabootcamp.proyecto_integrador3.Servicios.ServicioCliente;
import com.javabootcamp.proyecto_integrador3.Servicios.ServicioConsulta;
import com.javabootcamp.proyecto_integrador3.Servicios.ServicioProfesional;
import com.javabootcamp.proyecto_integrador3.Utilidad.Dias;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/consulta")
public class ControladorConsulta {

	 @Autowired
	    private ServicioProfesional servicioProfesional;

	    @Autowired
	    private ServicioConsulta servicioConsulta;
	    
	    @Autowired
	    private ServicioCliente servicioCliente;

	  //  @PreAuthorize("hasAnyRole('ROLE_PROFESIONAL')")
	    @GetMapping("/paciente")
	    public String pacientes(HttpSession session, ModelMap modelo) {
	        Profesional profesional = (Profesional) session.getAttribute("usuariosession");
	        modelo.addAttribute("pacientes", servicioConsulta.listarPacientes(profesional.getId()));
	        return "pacientes_paso1.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PROFESIONAL')")
	    @GetMapping("/consultas")    
	    public String listarConsultasPaciente(ModelMap modelo, @RequestParam String idPaciente){
	        modelo.addAttribute("consulta", servicioConsulta.listarHistorial(idPaciente));
	        modelo.addAttribute("cliente", servicioCliente.listarUnCliente(idPaciente));
	        return "pacientes_paso2.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PROFESIONAL')")
	    @GetMapping("/diagnostico")
	    public String darDiagnostico(ModelMap modelo, @RequestParam String id, String diagnostico, @RequestParam String idPaciente) {
	        System.out.println("diagnostico: " + diagnostico + " id consulta: " + id + " id paciente: " + idPaciente);
	        servicioConsulta.ingresarDiagnostico(id, diagnostico);
	        modelo.addAttribute("consulta", servicioConsulta.listarHistorial(idPaciente));
	        modelo.addAttribute("paciente", servicioCliente.listarUnCliente(idPaciente));
	        return "pacientes_paso2.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
	    @GetMapping("/provincia")
	    public String provincias(HttpSession session, ModelMap modelo) {
	        modelo.put("provincias", servicioProfesional.listarProvincias());
	        return "consulta_paso1.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
	    @GetMapping("/especialidad")
	    public String especialidades(@RequestParam String provincia, HttpSession session, ModelMap modelo) {
	        modelo.put("provincia", provincia);
	        modelo.put("especialidades", servicioProfesional.listarEspecialidadesPorProvincia(provincia));
	        return "consulta_paso2.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
	    @GetMapping("/profesional")
	    public String profesionales(@RequestParam String provincia, @RequestParam String especialidad, HttpSession session,
	            ModelMap modelo) {
	        modelo.put("profesionales",
	        		servicioProfesional.listarProfesionalPorEspecialidadesPorProvincia(provincia, especialidad));
	        return "consulta_paso3.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
	    @GetMapping("/disponibilidad")
	    public String disponibilidadProfesional(@RequestParam String idProfesional, HttpSession session, ModelMap modelo) {
	        Profesional profesional = servicioProfesional.getOne(idProfesional);
	        modelo.put("profesional", profesional);

	        ArrayList<String> listaA = new ArrayList<>();
	        LocalDate today = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
	        Dias dias = new Dias(profesional.getDisponibilidad().getDias());
	        for (LocalDate date = today; date.isBefore(today.plusDays(91)); date = date.plusDays(1)) {
	            if (dias.comprobar(date.getDayOfWeek())) {
	                listaA.add(date.format(formatter));
	            }
	        }
	        Integer nTurnos = profesional.getDisponibilidad().totalDeTurnos();
	        List<String> listaB = servicioConsulta.listarConsultasPorProfesionalAgrupadoPorFecha(profesional, nTurnos);
	        for (String item : listaB) {
	            listaA.remove(item);
	        }
	        modelo.put("fechas", listaA);
	        return "consulta_paso4.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
	    @GetMapping("/horario")
	    public String horarioProfesional(@RequestParam String idProfesional, @RequestParam String fecha,
	            HttpSession session, ModelMap modelo) {
	        Profesional profesional = servicioProfesional.getOne(idProfesional);
	        modelo.put("profesional", profesional);
	        modelo.put("fecha", fecha);
	        ArrayList<Integer> listaA = new ArrayList<>();
	        Integer entrada = profesional.getDisponibilidad().getEntrada();
	        Integer inicioDescanso = profesional.getDisponibilidad().getInicioDescanso();
	        for (int i = entrada; i < inicioDescanso; i++) {
	            listaA.add(i);
	        }
	        Integer finDescanso = profesional.getDisponibilidad().getFinDescanso();
	        Integer salida = profesional.getDisponibilidad().getSalida();
	        for (int i = finDescanso; i < salida; i++) {
	            listaA.add(i);
	        }
	        List<Integer> listaB = servicioConsulta.listarHorarioPorProfesionalPorFecha(profesional, fecha);
	        for (Integer item : listaB) {
	            listaA.remove(item);
	        }
	        modelo.put("horarios", listaA);
	        return "consulta_paso5.html";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
	    @GetMapping("/reservar")
	    public String reservar(@RequestParam String idProfesional, @RequestParam String fecha,
	            @RequestParam Integer horario, HttpSession session, ModelMap modelo) {
	        Cliente cliente = (Cliente) session.getAttribute("usuariosession");
	        servicioConsulta.guardarConsulta(idProfesional, cliente.getId(), fecha, horario);
	        return "redirect:/";
	    }

	   // @PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
	    @GetMapping("/eliminar/{id}")
	    public String eliminarConsulta(@PathVariable String id, Model modelo, HttpSession session) {
	    	servicioConsulta.eliminar(id);
	    	Cliente paciente = (Cliente) session.getAttribute("usuariosession");

	        List<Consulta> consulta = servicioConsulta.listarConsulta(paciente.getId());

	        modelo.addAttribute("consulta", consulta);
	        return "consulta_paciente.html";
	    }
	    
	  /*  @PostMapping("/calificar/{id}")
	    public String guardarCalificacion(@PathVariable String id, @RequestParam int calificacion, ModelMap modelo){
	    	servicioConsulta.guardarCalificacion(id, calificacion);
	        Consulta consulta = consultaService.buscarConsulta(id);
	        String idProfesional = consulta.getProfesional().getId();
	        Double promedio = consultaService.promedioCalificacionPorProfesional(idProfesional);
	        profesionalService.guardarCalificacion(idProfesional, promedio);
	        return "redirect:/paciente/consulta";
	    }*/
}
