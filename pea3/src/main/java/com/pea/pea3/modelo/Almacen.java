package com.pea.pea3.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Almacen")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Almacen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idalmacen;
	private String direccion;
	private String estado;
	
	

}
