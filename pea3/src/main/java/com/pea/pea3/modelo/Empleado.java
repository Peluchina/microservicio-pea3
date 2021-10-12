package com.pea.pea3.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleado")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idempleado;
	
	@NotEmpty(message = "El nombre no debe ser vacÃ­o")
	private String emplnombre;
	private String emplapellidoP;
	private String emplapellidoM;
	private String emplsexo;
	private String emplfechaN;
	private String emplcargo;
}
