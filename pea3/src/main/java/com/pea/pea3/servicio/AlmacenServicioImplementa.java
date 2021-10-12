package com.pea.pea3.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pea.pea3.modelo.Almacen;
import com.pea.pea3.repositorio.AlmacenRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class AlmacenServicioImplementa implements AlmacenServicio {
	public final AlmacenRepositorio almacenRepositorio;
	@Override
	public List<Almacen> listAllAlmacen() {
		// TODO Auto-generated method stub
		return almacenRepositorio .findAll();
	}

	@Override
	public Almacen getAlmacen(Long id) {
		// TODO Auto-generated method stub
		return almacenRepositorio.findById(id).orElse(null);
	}

	@Override
	public Almacen createAlmacen(Almacen almacen) {
		almacen.setEstado("CREADO");
		
		return almacenRepositorio.save(almacen);
	}

	@Override
	public Almacen updateAlmacen(Almacen almacen) {
		Almacen almacenUpdate = getAlmacen(almacen.getIdalmacen());
		
		if(almacenUpdate == null) {
			return null;
		}
		
		almacenUpdate.setDireccion(almacen.getDireccion());
		
		return almacenRepositorio.save(almacenUpdate);
	}

	@Override
	public Almacen deleteAlmacen(Long id) {
		Almacen almacenDelete = getAlmacen(id);
		
		if(almacenDelete == null) {
			return null;
		}
		
		almacenDelete.setEstado("ELIMINADO");
		
		return almacenRepositorio.save(almacenDelete);
	}

}
