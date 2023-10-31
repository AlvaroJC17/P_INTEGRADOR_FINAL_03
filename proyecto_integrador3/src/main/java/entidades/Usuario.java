package entidades;

import org.hibernate.annotations.GenericGenerator;

import enums.Rol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;

@Entity
@Inheritance
public class Usuario {
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "uuid2")
    protected String id;
    
    protected String email;
    protected String password;

    @Enumerated(EnumType.STRING)
    protected Rol rol;
    
    protected Boolean activo;
    
    //Constructores
    public Usuario() {}

	public Usuario(String id, String email, String password, Rol rol, Boolean activo) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.activo = activo;
	}

	//Setters y Getters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	//Metodos
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", rol=" + rol + ", activo="
				+ activo + "]";
	};
	
	
    
    

}
