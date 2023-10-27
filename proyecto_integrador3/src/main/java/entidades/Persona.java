package entidades;

import java.util.Date;

import enums.Sexo;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Inheritance
public abstract class Persona extends Usuario {

	protected Integer dni;

	protected String nombre;
	protected String apellido;

	@Enumerated(EnumType.STRING)
	protected Sexo sexo;

	@Temporal(TemporalType.DATE)
	protected Date fechaNacimiento;

	private String domicilio;
	
	private Integer telefono;

}