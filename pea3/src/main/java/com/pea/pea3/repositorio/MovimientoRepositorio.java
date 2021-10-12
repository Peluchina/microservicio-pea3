package com.pea.pea3.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pea.pea3.modelo.Almacen;
import com.pea.pea3.modelo.Empleado;
import com.pea.pea3.modelo.Movimiento;
import com.pea.pea3.modelo.Producto;


public interface MovimientoRepositorio extends JpaRepository<Movimiento, Long> {

	public List<Movimiento> findByAlmacen(Almacen almacen);
	
	public List<Movimiento> findByEmpleado(Empleado empleado);
	
	public List<Movimiento> findByProducto(Producto producto);
}
