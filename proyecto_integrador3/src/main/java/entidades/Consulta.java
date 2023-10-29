package entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Consulta {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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

}
