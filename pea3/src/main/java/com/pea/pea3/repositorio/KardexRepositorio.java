package com.pea.pea3.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pea.pea3.modelo.Kardex;
import com.pea.pea3.modelo.Movimiento;

public interface KardexRepositorio extends JpaRepository<Kardex, Long> {
	
	public List<Kardex> findByMovimiento(Movimiento movimiento);

}
