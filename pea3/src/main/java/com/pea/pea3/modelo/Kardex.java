package com.pea.pea3.modelo;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "kardex")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Kardex {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idkardex;
	
	@Positive(message = "El stock debe ser mayor que cero")
	private Integer cantidadinicial;
	private Integer cantidadfinal;
	private String estado;
	
	@Positive(message = "El total debe ser mayor que cero")
	private Double total;
	private Double totalfin;
	
	@NotNull(message = "El movimiento no puede ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idmovimientos")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Movimiento movimiento;
	
	
}
