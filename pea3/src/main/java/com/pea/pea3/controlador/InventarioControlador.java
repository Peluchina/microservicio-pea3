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
import com.pea.pea3.modelo.Inventario;
import com.pea.pea3.modelo.Movimiento;
import com.pea.pea3.servicio.InventarioServicio;


@RestController
@RequestMapping(value = "/inventario")
public class InventarioControlador {

	@Autowired
	InventarioServicio inventarioServicio;
	
	//@GetMapping("/")
		@RequestMapping(value = "/", method = RequestMethod.GET)
		public ResponseEntity<List<Inventario>> ListarInventario(@RequestParam(name = "movimientosId",
		required = false) Long movimientosId) {
			
			List<Inventario> inventarios = new ArrayList<>();
			
			if(movimientosId == null) {
				inventarios = inventarioServicio.listAllInventario();
				if(inventarios.isEmpty()) {
					return ResponseEntity.noContent().build();
				}
			}else {
				inventarios = inventarioServicio.findByMovimiento(Movimiento.builder()
						.idmovimientos(movimientosId).build());
				if(inventarios.isEmpty()) {
					return ResponseEntity.noContent().build();
				}
			}
			
			return ResponseEntity.ok(inventarios);
		}
		
		@RequestMapping(value = "/{id}",method = RequestMethod.GET)
		public ResponseEntity<Inventario> getInventario(@PathVariable("id") Long id){
			
			Inventario inventario = inventarioServicio.getInventario(id);
			if(inventario == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(inventario);
		}
		//@RequestMapping(value = "/",method = RequestMethod.POST)
		@PostMapping
		public ResponseEntity<Inventario> CrearProducto(@Valid @RequestBody Inventario inventario,BindingResult result){
			
			if(result.hasErrors()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
			}

			Inventario inventarioCreado = inventarioServicio.createInventario(inventario);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(inventarioCreado);
		}
		
		@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
		public ResponseEntity<Inventario> actualizarProducto(@PathVariable("id") Long id,
				@RequestBody Inventario inventario){
			
			inventario.setIdinventario(id);
			Inventario inventarioEncontrado = inventarioServicio.updateInventario(inventario);
			
			if(inventarioEncontrado == null) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.ok(inventarioEncontrado);
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
	    public ResponseEntity<Inventario> deleteInventario(@PathVariable("id") long id){
	    	Inventario inventarioDelete=inventarioServicio.deleteInventario(id);
	    	if(inventarioDelete==null){
	    		return ResponseEntity.notFound().build();
	    	}
	    	return ResponseEntity.ok(inventarioDelete);
	    	
	    }
}
