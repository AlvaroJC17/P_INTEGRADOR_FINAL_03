package entidades;

import org.hibernate.annotations.GenericGenerator;

import enums.Rol;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Usuario {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    protected String id;
    
    protected String email;
    protected String password;

    @Enumerated(EnumType.STRING)
    protected Rol rol;
    
    protected Boolean activo;

}
