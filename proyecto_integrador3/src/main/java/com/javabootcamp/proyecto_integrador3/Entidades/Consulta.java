package com.javabootcamp.proyecto_integrador3.Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    @OneToOne
    private Profesional profesional;

    @OneToOne
    private Cliente cliente;

    @Column(name = "diagnostico", length = 6000)
    private String diagnostico;

    private String fecha;

    private Integer horario;

    private Double precio;
    
    
    //Constructores
    public Consulta() {}

	public Consulta(String id, Profesional profesional, Cliente cliente, String diagnostico, String fecha,
			Integer horario, Double precio) {
		super();
		this.id = id;
		this.profesional = profesional;
		this.cliente = cliente;
		this.diagnostico = diagnostico;
		this.fecha = fecha;
		this.horario = horario;
		this.precio = precio;
	}

	//Setters y Getters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getHorario() {
		return horario;
	}

	public void setHorario(Integer horario) {
		this.horario = horario;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	//Metodos
	@Override
	public String toString() {
		return "Consulta [id=" + id + ", profesional=" + profesional + ", cliente=" + cliente + ", diagnostico="
				+ diagnostico + ", fecha=" + fecha + ", horario=" + horario + ", precio=" + precio + "]";
	};
    
}
