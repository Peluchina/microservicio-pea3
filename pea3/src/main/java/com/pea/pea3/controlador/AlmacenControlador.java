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
import com.pea.pea3.modelo.Almacen;
import com.pea.pea3.servicio.AlmacenServicio;

@RestController
@RequestMapping(value = "/almacen")
public class AlmacenControlador {
	@Autowired
	AlmacenServicio almacenServicio;
	
	//@GetMapping("/")
			@RequestMapping(value = "/", method = RequestMethod.GET)
			public ResponseEntity<List<Almacen>> ListarAlmacen(@RequestParam(name = "almacenId",
			required = false) Long Id) {
				
				List<Almacen> almacen = new ArrayList<>();			
				
					almacen = almacenServicio.listAllAlmacen();
					
				return ResponseEntity.ok(almacen);
			}
			
			@RequestMapping(value = "/{id}",method = RequestMethod.GET)
			public ResponseEntity<Almacen> getAlmacen(@PathVariable("id") Long id){
				
				Almacen almacen = almacenServicio.getAlmacen(id);
				if(almacen == null) {
					return ResponseEntity.notFound().build();
				}
				
				return ResponseEntity.ok(almacen);
			}
			//@RequestMapping(value = "/",method = RequestMethod.POST)
			@PostMapping
			public ResponseEntity<Almacen> CrearAlmacen(@Valid @RequestBody Almacen almacen,BindingResult result){
				
				if(result.hasErrors()) {
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
				}

				Almacen almacenCreado = almacenServicio.createAlmacen(almacen);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(almacenCreado);
			}
			
			@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
			public ResponseEntity<Almacen> actualizarAlmacen(@PathVariable("id") Long id,
					@RequestBody Almacen almacen){
				
				almacen.setIdalmacen(id);
				Almacen almacenEncontrado = almacenServicio.updateAlmacen(almacen);
				
				if(almacenEncontrado == null) {
					return ResponseEntity.notFound().build();
				}
				
				return ResponseEntity.ok(almacenEncontrado);
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
		    public ResponseEntity<Almacen> deleteAlmacen(@PathVariable("id") long id){
		    	Almacen almacenDelete=almacenServicio.deleteAlmacen(id);
		    	if(almacenDelete==null){
		    		return ResponseEntity.notFound().build();
		    	}
		    	return ResponseEntity.ok(almacenDelete);
		    	
		    }
}
