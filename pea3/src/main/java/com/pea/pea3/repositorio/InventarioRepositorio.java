package com.pea.pea3.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pea.pea3.modelo.Inventario;
import com.pea.pea3.modelo.Movimiento;

public interface InventarioRepositorio extends JpaRepository<Inventario, Long>{
	public List<Inventario> findByMovimiento(Movimiento movimiento);

}
