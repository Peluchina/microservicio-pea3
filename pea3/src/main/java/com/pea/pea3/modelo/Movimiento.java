package com.pea.pea3.modelo;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "movimientos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idmovimientos;
	
	@NotEmpty(message = "El nombre no debe ser vacÃ­o")
	private String tipomovimiento;
	
	@Positive(message = "La cantidad debe ser mayor que cero")
	private Integer cantidad;
	private String estado;
	
	@Column(name="fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	@NotNull(message = "La categoria no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproducto")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Producto producto;
	
	@NotNull(message = "La categoria no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idalmacen")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Almacen almacen;

	@NotNull(message = "La categoria no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idempleado")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Empleado empleado;
}
