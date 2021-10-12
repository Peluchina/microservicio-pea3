package com.pea.pea3.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "proveedor")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproveedor;
	
	@NotEmpty(message = "El nombre no debe ser vacÃ­o")
	private String provnombre;
	private String provsucursal;
	private String provdireccion;
	private String provcorreo;
	private Integer estado;
	private Integer provtelefono;
	
	
	
}
