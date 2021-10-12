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
import com.pea.pea3.modelo.Kardex;
import com.pea.pea3.modelo.Movimiento;

import com.pea.pea3.servicio.KadexServicio;


@RestController
@RequestMapping(value = "/kardex")
public class KardexControlador {
	
	@Autowired
	KadexServicio kadexServicio;
	/*listar*/
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Kardex>> ListarKardex(@RequestParam(name = "movimientoId",
	required = false) Long movimientoId) {
		
		List<Kardex> kardexs = new ArrayList<>();
		
		if(movimientoId == null) {
			kardexs = kadexServicio.listAllKardex();
			if(kardexs.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}else {
			kardexs = kadexServicio.findByMovimiento(Movimiento.builder()
					.idmovimientos(movimientoId).build());
			if(kardexs.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		}
		
		return ResponseEntity.ok(kardexs);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Kardex> getKardex(@PathVariable("id") Long id){
		
		Kardex kardex = kadexServicio.getKardex(id);
		if(kardex == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(kardex);
	}
	
	//@RequestMapping(value = "/",method = RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Kardex> CrearKardex(@Valid @RequestBody Kardex kardex,BindingResult result){
		
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
		}

		Kardex kardexCreado = kadexServicio.createKardex(kardex);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(kardexCreado);
	}
	
	@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Kardex> actualizarKardex(@PathVariable("id") Long id,
			@RequestBody Kardex kardex){
		
		kardex.setIdkardex(id);
		Kardex kardexEncontrado = kadexServicio.updateKardex(kardex);
		
		if(kardexEncontrado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(kardexEncontrado);
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
    public ResponseEntity<Kardex> deleteKardex(@PathVariable("id") long id){
    	Kardex kardexDelete=kadexServicio.deleteKardex(id);
    	if(kardexDelete==null){
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(kardexDelete);
    	
    }

}
