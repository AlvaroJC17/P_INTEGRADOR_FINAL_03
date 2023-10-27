package entidades;

import enums.Provincias;
import enums.Tratamiento;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
//import jakarta.persistence.OneToOne;

@Entity
public final class Profesional extends Persona {
	
    private String matricula;

    @Enumerated(EnumType.STRING)
    private Tratamiento tratamiento;

    //@OneToOne
    //private Disponibilidad disponibilidad;   ESTUDIAR LA DISPONIBILIDAD DE TURNOS COMO UNA CLASE
    
    @Enumerated(EnumType.STRING)
    private Provincias provincia;

}
