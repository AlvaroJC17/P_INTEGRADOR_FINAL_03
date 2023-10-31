package entidades;

import java.util.Arrays;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
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
    
    
    //Constructores
    public Disponibilidad() {};
    
   
    public Disponibilidad(String id, int entrada, int salida, int inicioDescanso, int finDescanso, String[] dias) {
		super();
		this.id = id;
		this.entrada = entrada;
		this.salida = salida;
		this.inicioDescanso = inicioDescanso;
		this.finDescanso = finDescanso;
		this.dias = dias;
	}
    
    
    //Setters y Getters
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getEntrada() {
		return entrada;
	}


	public void setEntrada(int entrada) {
		this.entrada = entrada;
	}


	public int getSalida() {
		return salida;
	}


	public void setSalida(int salida) {
		this.salida = salida;
	}


	public int getInicioDescanso() {
		return inicioDescanso;
	}


	public void setInicioDescanso(int inicioDescanso) {
		this.inicioDescanso = inicioDescanso;
	}


	public int getFinDescanso() {
		return finDescanso;
	}


	public void setFinDescanso(int finDescanso) {
		this.finDescanso = finDescanso;
	}


	public String[] getDias() {
		return dias;
	}


	public void setDias(String[] dias) {
		this.dias = dias;
	}


	//Metodos
	
	public int totalDeTurnos(){
        return ((salida - entrada) - (finDescanso - inicioDescanso));
    }

	@Override
	public String toString() {
		return "Disponibilidad [id=" + id + ", entrada=" + entrada + ", salida=" + salida + ", inicioDescanso="
				+ inicioDescanso + ", finDescanso=" + finDescanso + ", dias=" + Arrays.toString(dias) + "]";
	}
	
	

}
