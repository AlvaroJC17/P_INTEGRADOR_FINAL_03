package entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Disponibilidad {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid")
    private String id;

    private int  entrada;

    private int salida;

    private int inicioDescanso;

    private int finDescanso;

    private String[] dias;

    public int totalDeTurnos(){
        return ((salida - entrada) - (finDescanso - inicioDescanso));
    }

}
