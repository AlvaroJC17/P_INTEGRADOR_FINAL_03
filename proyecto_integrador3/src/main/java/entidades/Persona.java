package entidades;

import java.util.Date;

import enums.Rol;
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
	
	//Constructores
	public Persona() {}

	public Persona(Integer dni, String nombre, String apellido, Sexo sexo, Date fechaNacimiento, String domicilio,
			Integer telefono, String id, String email, String password, Rol rol, Boolean activo ) {
		super(id, email, password, rol, activo);
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.telefono = telefono;
	}
	
	//Setters y Getters
	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	//Metodos
	@Override
	public String toString() {
		return super.toString() + "Persona [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", fechaNacimiento=" + fechaNacimiento + ", domicilio=" + domicilio + ", telefono=" + telefono + "]";
	}

	
	

}