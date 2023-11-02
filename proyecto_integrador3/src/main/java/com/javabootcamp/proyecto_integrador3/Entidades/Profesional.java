package com.javabootcamp.proyecto_integrador3.Entidades;

import java.util.Date;

import com.javabootcamp.proyecto_integrador3.Enums.Provincias;
import com.javabootcamp.proyecto_integrador3.Enums.Rol;
import com.javabootcamp.proyecto_integrador3.Enums.Sexo;
import com.javabootcamp.proyecto_integrador3.Enums.Tratamiento;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;

@Entity
public class Profesional extends Persona {

	//Atributos
    private String matricula;

    @Enumerated(EnumType.STRING)
    private Tratamiento tratamiento;

    @OneToOne
    private Disponibilidad disponibilidad;   //ESTUDIAR LA DISPONIBILIDAD DE TURNOS COMO UNA CLASE
    
    @Enumerated(EnumType.STRING)
    private Provincias provincia;
    
    //Constructores
    public Profesional() {}

	public Profesional(String matricula, Tratamiento tratamiento, Disponibilidad disponibilidad, Provincias provincia,
			String nombre, String apellido, Sexo sexo, Date fechaNacimiento, String domicilio,
			Integer telefono, String id, String dni, String email, String password, Rol rol, Boolean activo) {
		
		super(nombre, apellido, sexo, fechaNacimiento, domicilio, telefono, id, dni, email, password, rol, activo);
		this.matricula = matricula;
		this.tratamiento = tratamiento;
		this.disponibilidad = disponibilidad;
		this.provincia = provincia;
	}

	//Setters y Getters
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Tratamiento getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(Tratamiento tratamiento) {
		this.tratamiento = tratamiento;
	}

	public Disponibilidad getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Disponibilidad disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Provincias getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincias provincia) {
		this.provincia = provincia;
	}

	//Metodos
	@Override
	public String toString() {
		return super.toString() +  "Profesional [matricula=" + matricula + ", tratamiento=" + tratamiento + ", disponibilidad="
				+ disponibilidad + ", provincia=" + provincia + "]";
	}

	
	
}
