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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@GenericGenerator(name = "uuid")
    protected String id;
    
    protected String email;
    protected String password;

    @Enumerated(EnumType.STRING)
    protected Rol rol;
    
    protected Boolean activo;

}
