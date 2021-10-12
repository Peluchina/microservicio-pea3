package com.pea.pea3.servicio;

import java.util.List;

import com.pea.pea3.modelo.Almacen;


public interface AlmacenServicio {

	public List<Almacen> listAllAlmacen();
	public Almacen getAlmacen(Long id);
	
	public Almacen createAlmacen(Almacen almacen);
	public Almacen updateAlmacen(Almacen almacen);
	public Almacen deleteAlmacen(Long id);
}
