package com.javabootcamp.proyecto_integrador3.Entidades;

import java.util.Date;

import com.javabootcamp.proyecto_integrador3.Enums.Rol;
import com.javabootcamp.proyecto_integrador3.Enums.Sexo;

import jakarta.persistence.Entity;


@Entity
public class Cliente extends Persona {

private Integer edad;
	
	//Constructores
	public Cliente(){
		
	}

	
	public Cliente(Integer edad, String nombre, String apellido, Sexo sexo, Date fechaNacimiento, String domicilio, Integer telefono,
			String id, String dni, String email, String password, Rol rol, Boolean activo) {
		super(nombre, apellido, sexo, fechaNacimiento, domicilio, telefono, id, dni, email, password, rol, activo);
		this.edad = edad;
	}

	//Setters y Getters
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	//Metodos
	@Override
	public String toString() {
		return super.toString() + "Cliente [edad=" + edad + "]";
	}
}
