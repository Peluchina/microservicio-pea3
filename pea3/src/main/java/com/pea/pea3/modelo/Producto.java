package com.pea.pea3.modelo;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Producto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idproducto;
	
	@NotEmpty(message = "El nombre no debe ser vacÃ­o")
	private String prodnombre;
	private String proddescripcion;
	private String prodcategoria;
	
	@Positive(message = "El stock debe ser mayor que cero")
	private Double costo;
	private Double prodprecio;
	private Integer estado;
	
	@NotNull(message = "El proveedor no puede ser vacio")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproveedor")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Proveedor proveedor;
	
	
}
