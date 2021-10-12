package com.pea.pea3.servicio;


import java.util.List;

import org.springframework.stereotype.Service;

import com.pea.pea3.modelo.Inventario;
import com.pea.pea3.modelo.Movimiento;
import com.pea.pea3.repositorio.InventarioRepositorio;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class InventarioServicioImplementa implements InventarioServicio {
	public final InventarioRepositorio inventarioRepositorio;
	
	
	@Override
	public List<Inventario> listAllInventario() {
		// TODO Auto-generated method stub
		return inventarioRepositorio.findAll();
	}

	@Override
	public Inventario getInventario(Long id) {
		// TODO Auto-generated method stub
		return inventarioRepositorio.findById(id).orElse(null);
	}

	@Override
	public Inventario createInventario(Inventario inventario) {
		inventario.setEstado("CREADO");
		
		return inventarioRepositorio.save(inventario);
	}

	@Override
	public Inventario updateInventario(Inventario inventario) {
		Inventario inventarioUpdate = getInventario(inventario.getIdinventario());
		
		if(inventarioUpdate == null) {
			return null;
		}
		
		inventarioUpdate.setCantidadinicial(inventario.getCantidadinicial());
		inventarioUpdate.setMovimiento(inventario.getMovimiento());
		inventarioUpdate.setStock(inventario.getStock());
		
		return inventarioRepositorio.save(inventarioUpdate);
	}

	@Override
	public Inventario deleteInventario(Long id) {
		Inventario inventarioDelete = getInventario(id);
		
		if(inventarioDelete == null) {
			return null;
		}
		
		inventarioDelete.setEstado("ELIMINADO");
		
		return inventarioRepositorio.save(inventarioDelete);
	}

	@Override
	public List<Inventario> findByMovimiento(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return inventarioRepositorio.findByMovimiento(movimiento);
	}

	@Override
	public Inventario updatestock(Long id, Integer quantity) {
		Inventario inventarioUpdateStock = getInventario(id);
		
		if(inventarioUpdateStock == null) {
			return null;
		}
		
		Integer stock = inventarioUpdateStock.getStock() + quantity;
		
		inventarioUpdateStock.setStock(stock);
		
		return inventarioRepositorio.save(inventarioUpdateStock);
	}

	@Override
	public Inventario updatecantidadinicial(Long id, Integer quantity) {
		// TODO Auto-generated method stub
		Inventario inventarioUpdatecantidadinicial = getInventario(id);
		
		if(inventarioUpdatecantidadinicial == null) {
			return null;
		}
		
		Integer cantidadinicial = inventarioUpdatecantidadinicial.getCantidadinicial() + quantity;
		
		inventarioUpdatecantidadinicial.setCantidadinicial(cantidadinicial);
		
		return inventarioRepositorio.save(inventarioUpdatecantidadinicial);
	}

}
