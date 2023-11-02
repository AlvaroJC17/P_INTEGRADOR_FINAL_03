package servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entidades.Cliente;
import entidades.Consulta;
import entidades.Profesional;
import repositorios.RepositorioCliente;
import repositorios.RepositorioConsulta;
import repositorios.RepositorioProfesional;

@Service
public class ServicioConsulta {
	
	@Autowired
	private RepositorioConsulta repositorioConsulta;

    @Autowired
    private RepositorioProfesional repositorioProfesional;

    @Autowired
    private RepositorioCliente repositorioCliente;

    @Transactional(readOnly = true)
    public List<Consulta> listarHistorial(String id) {
        return repositorioConsulta.listarConsulta(id);
    }
    
    @Transactional
    public void ingresarDiagnostico(String id, String diagnostico) {

        Optional<Consulta> resp = repositorioConsulta.findById(id);
       
        Consulta consulta = new Consulta();
        if (resp.isPresent()) {
            consulta = resp.get();
            consulta.setDiagnostico(diagnostico);
            repositorioConsulta.save(consulta);
        }
    }
        
    @Transactional(readOnly = true)
    public List<Cliente> listarPacientes(String id) {
        return repositorioConsulta.listarClientesPorProfesional(id);
    }    

    @Transactional(readOnly = true)
    public List<String> listarConsultasPorProfesionalAgrupadoPorFecha(Profesional profesional, Integer nTurnos) {
        return repositorioConsulta.listarConsultasPorProfesionalAgrupadoPorFecha(profesional, nTurnos);
    }

    @Transactional(readOnly = true)
    public List<Integer> listarHorarioPorProfesionalPorFecha(Profesional profesional, String fecha) {
        return repositorioConsulta.listarHorarioPorProfesionalPorFecha(profesional, fecha);
    }
 
    @Transactional
    public void guardarConsulta(String idProfesional, String idCliente, String fecha, Integer horario) {
        Optional<Profesional> respuestaProfesional = repositorioProfesional.findById(idProfesional);
        Optional<Cliente> respuestaCliente = repositorioCliente.findById(idCliente);
        if (respuestaProfesional.isPresent() && respuestaCliente.isPresent()) {
            Profesional profesional = respuestaProfesional.get();
            Cliente cliente = respuestaCliente.get();
            Consulta consulta = new Consulta();
            consulta.setProfesional(profesional);
            consulta.setCliente(cliente);
            consulta.setFecha(fecha);
            consulta.setHorario(horario);
            consulta.setDiagnostico(null);
            repositorioConsulta.save(consulta);
        }
    }
    
    @Transactional(readOnly = true)
    public List<Consulta> listarConsulta(String id) {
        return repositorioConsulta.listarConsulta(id);
    }

    @Transactional
    public void eliminar(String id) {
        Optional<Consulta> resp = repositorioConsulta.findById(id);
        Consulta consulta = new Consulta();
        if (resp.isPresent()) {
            consulta = resp.get();
            repositorioConsulta.delete(consulta);
        }
    }
    
    @Transactional
    public Consulta buscarConsulta(String id) {
        Optional<Consulta> resp = repositorioConsulta.findById(id);
        if (resp.isPresent()) {
            return resp.get();
        }
        return null;
    }

   /* @Transactional
    public void guardarCalificacion(String id, Integer calificacion) {
        Optional<Consulta> resp = repositorioConsulta.findById(id);
        Consulta consulta = new Consulta();
        if (resp.isPresent()) {
            consulta = resp.get();
            consulta.setCalificacion(calificacion);
            repositorioConsulta.save(consulta);
        }
    }

    @Transactional(readOnly = true)
    public Double promedioCalificacionPorProfesional(String id) {
        try {
            return repositorioConsulta.promedioCalificacionPorProfesional(id);   
        } catch (Exception e) {
            return 0d;
        }
    }*/

  
        
}
