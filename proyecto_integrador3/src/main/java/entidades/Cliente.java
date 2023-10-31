package entidades;

import java.util.Date;

import enums.Rol;
import enums.Sexo;
import jakarta.persistence.Entity;

@Entity
public final class Cliente extends Persona {
	
	private Integer edad;
	
	//Constructores
	Cliente(){}

	public Cliente(Integer edad, Integer dni, String nombre, String apellido, Sexo sexo, Date fechaNacimiento, String domicilio,
			Integer telefono, String id, String email, String password, Rol rol, Boolean activo ) {
		super(dni, nombre, apellido, sexo, fechaNacimiento, domicilio, telefono, id, email, password, rol, activo);
		this.edad = edad;
	}

	//Setter y Getters
	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	//Metodos
	@Override
	public String toString() {
		return "Cliente [edad=" + edad + "]";
	}

	
	
	
	

}
