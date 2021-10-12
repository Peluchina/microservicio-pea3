package com.pea.pea3.servicio;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pea.pea3.modelo.Almacen;
import com.pea.pea3.modelo.Empleado;
import com.pea.pea3.modelo.Movimiento;
import com.pea.pea3.modelo.Producto;
import com.pea.pea3.repositorio.MovimientoRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimientoServicioImplementa implements MovimientoServicio {

	public final MovimientoRepositorio movimientoRepositorio; 
	
	@Override
	public List<Movimiento> listAllMovimiento() {
		// TODO Auto-generated method stub
		return movimientoRepositorio.findAll();
	}

	@Override
	public Movimiento getMovimiento(Long id) {
		// TODO Auto-generated method stub
		return movimientoRepositorio.findById(id).orElse(null);
	}

	@Override
	public Movimiento createMovimiento(Movimiento movimiento) {
		// TODO Auto-generated method stub
		movimiento.setFecha(new Date());
		movimiento.setEstado("CREADO");
	
		return movimientoRepositorio.save(movimiento);
	}

	@Override
	public Movimiento updateMovimiento(Movimiento movimiento) {
		Movimiento movimientoUpdate=getMovimiento(movimiento.getIdmovimientos());
		if(movimientoUpdate == null) {
			return null;
		}
		
		movimientoUpdate.setTipomovimiento(movimiento.getTipomovimiento());
		movimientoUpdate.setProducto(movimiento.getProducto());
		movimientoUpdate.setCantidad(movimiento.getCantidad());
		movimientoUpdate.setAlmacen(movimiento.getAlmacen());
		movimientoUpdate.setEmpleado(movimiento.getEmpleado());
		
		return movimientoRepositorio.save(movimientoUpdate);
		
	}

	@Override
	public Movimiento deleteMovimiento(Long id) {
		Movimiento movimientoDelete = getMovimiento(id);
		if(movimientoDelete == null) {
			return null;
		}
		
		movimientoDelete.setEstado("Eliminado");
		
		return movimientoRepositorio.save(movimientoDelete);
	}

	@Override
	public List<Movimiento> findByProducto(Producto producto) {
		// TODO Auto-generated method stub
		return movimientoRepositorio.findByProducto(producto);
	}

	@Override
	public List<Movimiento> findByAlmacen(Almacen almacen) {
		// TODO Auto-generated method stub
		return movimientoRepositorio.findByAlmacen(almacen);
	}

	@Override
	public List<Movimiento> findByEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return movimientoRepositorio.findByEmpleado(empleado);
	}

	@Override
	public Movimiento updatecantidad(Long id, Integer quantity) {
		Movimiento movimientoUpdateCantidad = getMovimiento(id);
		
		if(movimientoUpdateCantidad == null) {
			return null;
		}
		
		Integer cantidad = movimientoUpdateCantidad.getCantidad() + quantity;
		
		movimientoUpdateCantidad.setCantidad(cantidad);
		
		return movimientoRepositorio.save(movimientoUpdateCantidad);
	}

}
