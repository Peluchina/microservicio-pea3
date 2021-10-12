package com.pea.pea3.servicio;

import java.util.List;

import com.pea.pea3.modelo.Almacen;
import com.pea.pea3.modelo.Empleado;
import com.pea.pea3.modelo.Movimiento;
import com.pea.pea3.modelo.Producto;

public interface MovimientoServicio {

	public List<Movimiento> listAllMovimiento();
	public Movimiento getMovimiento(Long id);
	
	public Movimiento createMovimiento(Movimiento movimiento);
	public Movimiento updateMovimiento(Movimiento movimiento);
	public Movimiento deleteMovimiento(Long id);
	
	public List<Movimiento> findByProducto(Producto producto);
	public List<Movimiento> findByAlmacen(Almacen almacen);
	public List<Movimiento> findByEmpleado(Empleado empleado);
	
	public Movimiento updatecantidad(Long id, Integer quantity);
	
}
