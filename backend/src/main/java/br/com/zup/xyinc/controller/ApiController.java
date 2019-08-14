package br.com.zup.xyinc.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.xyinc.service.ApiService;
import br.com.zup.xyinc.util.ZupException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

	@ApiOperation(
			value="Listar todos os registros de uma entidade", 
			response=ResponseEntity.class, 
			notes="Informe a entidade a ser recuperada.")
    @RequestMapping(value = "/{entity}", method = RequestMethod.GET)
    public ResponseEntity getAll(@PathVariable String entity) {
        try {
            return ResponseEntity.ok(apiService.findAll(entity));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Recuperar um registro de uma entidade pelo ID", 
			response=ResponseEntity.class, 
			notes="Informe a entidade e ID do registro da entidade a ser recuperado.")
    @RequestMapping(value = "/{entity}/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable String entity, @PathVariable String id) {
        try {
            return ResponseEntity.ok(apiService.findById(entity, id));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
	
	@ApiOperation(
			value="Salvar um registro de uma entidade", 
			response=ResponseEntity.class, 
			notes="Informe a entidade e o JSON respectivo aos registro da entidade a ser persistida.")
    @RequestMapping(value = "/{entity}", method = RequestMethod.POST)
    public ResponseEntity create(@PathVariable String entity, @RequestBody Map<String, Object> attributes) {
        try {
            return ResponseEntity.ok(apiService.create(entity, attributes));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Remover um registro de uma entidade", 
			response=ResponseEntity.class, 
			notes="Informe a entidade e o ID respectivo aos registro da entidade a ser removido.")
    @RequestMapping(value = "/{entity}/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String entity, @PathVariable String id) {
        try {
            return ResponseEntity.ok(apiService.deleteById(entity, id));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Alterar um registro de uma entidade", 
			response=ResponseEntity.class, 
			notes="Informe a entidade e o JSON e ID respectivo aos registro da entidade a ser alterada.")
    @RequestMapping(value = "/{entity}/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String entity, @PathVariable String id, @RequestBody Map<String, Object> attributes) {
        try {
            return ResponseEntity.ok(apiService.update(entity, id, attributes));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
