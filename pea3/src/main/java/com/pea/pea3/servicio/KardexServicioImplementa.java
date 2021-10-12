package com.pea.pea3.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pea.pea3.modelo.Kardex;
import com.pea.pea3.modelo.Movimiento;
import com.pea.pea3.repositorio.KardexRepositorio;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class KardexServicioImplementa implements KadexServicio{

	public final KardexRepositorio kardexRepositorio;
	
	@Override
	public List<Kardex> listAllKardex() {
		// TODO Auto-generated method stub
		return kardexRepositorio.findAll();
	}

	@Override
	public Kardex getKardex(Long id) {
		// TODO Auto-generated method stub
		return kardexRepositorio.findById(id).orElse(null);
	}

	@Override
	public Kardex createKardex(Kardex kardex) {
		// TODO Auto-generated method stub
		
		return kardexRepositorio.save(kardex);
	}

	@Override
	public Kardex updateKardex(Kardex kardex) {
Kardex kardexUpdate = getKardex(kardex.getIdkardex());
		
		if(kardexUpdate == null) {
			return null;
		}
		
		kardexUpdate.setMovimiento(kardex.getMovimiento());
		kardexUpdate.setCantidadinicial(kardex.getCantidadinicial());
		kardexUpdate.setTotal(kardex.getTotal());
		kardexUpdate.setCantidadfinal(kardex.getCantidadfinal());
		kardexUpdate.setTotalfin(kardex.getTotalfin());
		
		
		return kardexRepositorio.save(kardexUpdate);
	}

	@Override
	public Kardex deleteKardex(Long id) {
		Kardex kardexDelete = getKardex(id);
		
		if(kardexDelete == null) {
			return null;
		}
		
		kardexDelete.setEstado("ELIMINADO");
		
		return kardexRepositorio.save(kardexDelete);
	}

	@Override
	public List<Kardex> findByMovimiento(Movimiento movimiento) {
		// TODO Auto-generated method stub
		return kardexRepositorio.findByMovimiento(movimiento);
	}

	@Override
	public Kardex updatecantidadinicial(Long id, Integer quantity) {
		// TODO Auto-generated method stub
		Kardex kardexUpdateCantidadI = getKardex(id);
		
		if(kardexUpdateCantidadI == null) {
			return null;
		}
		
		Integer cantidadinicial = kardexUpdateCantidadI.getCantidadinicial() + quantity;
		
		kardexUpdateCantidadI.setCantidadinicial(cantidadinicial);
		
		return kardexRepositorio.save(kardexUpdateCantidadI);
	}

	@Override
	public Kardex updatecantidadfinal(Long id, Integer quantity) {
Kardex kardexUpdateCantidadF = getKardex(id);
		
		if(kardexUpdateCantidadF == null) {
			return null;
		}
		
		Integer cantidadfinal = kardexUpdateCantidadF.getCantidadfinal() + quantity;
		
		kardexUpdateCantidadF.setCantidadfinal(cantidadfinal);
		
		return kardexRepositorio.save(kardexUpdateCantidadF);
	}

}
