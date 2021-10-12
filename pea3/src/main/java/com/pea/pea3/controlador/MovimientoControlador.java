package com.pea.pea3.controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pea.pea3.modelo.Movimiento;
import com.pea.pea3.modelo.Producto;
import com.pea.pea3.servicio.MovimientoServicio;




@RestController
@RequestMapping(value = "/movimientos")

public class MovimientoControlador {

	
	@Autowired
	MovimientoServicio movimientoServicio;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Movimiento>> ListarMovimiento(@RequestParam(name = "productoId",
	required = false) Long productoId) {
		
		List<Movimiento> movimientos = new ArrayList<>();
		
		if(productoId == null) {
			movimientos = movimientoServicio.listAllMovimiento();
			if(movimientos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			movimientos = movimientoServicio.findByProducto(Producto.builder()
					.idproducto(productoId).build());
			if(movimientos.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(movimientos);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Movimiento> getMovimiento(@PathVariable("id") Long id){
		
		Movimiento movimiento = movimientoServicio.getMovimiento(id);
		if(movimiento == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(movimiento);
	}
	
	//@RequestMapping(value = "/",method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Movimiento> CrearMovimiento(@Valid @RequestBody Movimiento movimiento,BindingResult result){
		
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
		}

		Movimiento movimientoCreado = movimientoServicio.createMovimiento(movimiento);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(movimientoCreado);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Movimiento> actualizarMovimiento(@PathVariable("id") Long id,
			@RequestBody Movimiento movimiento){
		
		movimiento.setIdmovimientos(id);
		Movimiento movimientoEncontrado = movimientoServicio.updateMovimiento(movimiento);
		
		if(movimientoEncontrado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(movimientoEncontrado);
	}
	
    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
	
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Movimiento> deleteMovimiento(@PathVariable("id") long id){
    	Movimiento movimientoDelete=movimientoServicio.deleteMovimiento(id);
    	if(movimientoDelete==null){
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(movimientoDelete);
    	
    }
	
}






