package br.com.zup.xyinc.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.xyinc.factory.EntityFactory;
import br.com.zup.xyinc.service.EntityService;
import br.com.zup.xyinc.util.ZupException;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;

	@ApiOperation(
			value="Listar todas as entidade", 
			response=ResponseEntity.class, 
			notes="Todas as entidades são recuperadas.")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity listAll() {
        try {
        	return ResponseEntity.ok(entityService.findAll());
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Recuperar uma entidade", 
			response=ResponseEntity.class, 
			notes="Informe o Id da entidade para ser recuperada.")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable String id) {
        try {
            return ResponseEntity.ok(entityService.findById(id));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Salvar uma entidade", 
			response=ResponseEntity.class, 
			notes="Informe o JSON com o nome do campo e tipo para ser salvo.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody EntityFactory entity) {
        try {
            return ResponseEntity.ok(entityService.create(entity));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

	@ApiOperation(
			value="Remover uma entidade", 
			response=ResponseEntity.class, 
			notes="Informe o ID de uma entidade para ser removida.")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(entityService.delete(id));
        } catch (ZupException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
