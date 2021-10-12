package com.pea.pea3.servicio;

import java.util.List;

import com.pea.pea3.modelo.Kardex;
import com.pea.pea3.modelo.Movimiento;

public interface KadexServicio {

	public List<Kardex> listAllKardex();
	public Kardex getKardex(Long id);
	
	public Kardex createKardex(Kardex kardex);
	public Kardex updateKardex(Kardex kardex);
	public Kardex deleteKardex(Long id);
	
	public List<Kardex> findByMovimiento(Movimiento movimiento);
	
	public Kardex updatecantidadinicial(Long id, Integer quantity);
	public Kardex updatecantidadfinal(Long id, Integer quantity);
}
