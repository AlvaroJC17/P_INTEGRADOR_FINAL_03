package com.javabootcamp.proyecto_integrador3.Entidades;

import java.util.Date;

import com.javabootcamp.proyecto_integrador3.Enums.Rol;
import com.javabootcamp.proyecto_integrador3.Enums.Sexo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Inheritance
public abstract class Persona extends Usuario{

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

	public Persona(String nombre, String apellido, Sexo sexo, Date fechaNacimiento, String domicilio,
			Integer telefono, String id, String dni, String email, String password, Rol rol, Boolean activo) {
		super(id, dni, email, password, rol, activo);
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.telefono = telefono;
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

	@Override
	public String toString() {
		return super.toString() + "Persona [nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo + ", fechaNacimiento="
				+ fechaNacimiento + ", domicilio=" + domicilio + ", telefono=" + telefono + "]";
	}

}
