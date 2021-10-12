package com.pea.pea3.servicio;

import java.util.List;

import com.pea.pea3.modelo.Inventario;
import com.pea.pea3.modelo.Movimiento;



public interface InventarioServicio {
	public List<Inventario> listAllInventario();
	public Inventario getInventario(Long id);
	
	public Inventario createInventario(Inventario inventario);
	public Inventario updateInventario(Inventario inventario);
	public Inventario deleteInventario(Long id);
	
	public List<Inventario> findByMovimiento(Movimiento movimiento);
	
	public Inventario updatestock(Long id, Integer quantity);
	public Inventario updatecantidadinicial(Long id, Integer quantity);
}
