package br.com.zup.xyinc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.xyinc.bean.EntityBean;
import br.com.zup.xyinc.factory.EntityFactory;
import br.com.zup.xyinc.persistence.IEntityPersistence;
import br.com.zup.xyinc.util.ZupException;

@Service
public class EntityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityService.class);

    @Autowired
    private IEntityPersistence entityPersistence;

    @Autowired
    private PersistenceService persistenceService;

    public List<Object> findAll() throws ZupException {
        LOGGER.info("EntityService - findAll - entidade");
        return entityPersistence
        		.findAll()
        		.stream()
        		.map(entity -> 
        		new EntityFactory(entity)
        	).collect(Collectors.toList());
    }

    public Object findById(String id) throws ZupException {
        LOGGER.info("EntityService - findById - id {}", id);
        EntityBean entityBean = persistenceService.findEntityByIdOrThrow(id);
        return new EntityFactory(entityBean);
    }

    //return ResponseEntity.status(HttpStatus.CREATED).body(new EntityFactory(entityService.create(entityDTO.unwrap())));
    public Object create(EntityFactory entityJson) throws ZupException {
        LOGGER.info("EntityService - create - entidade {}", entityJson);
        EntityBean entityBean = entityJson.unwrap();
        if (persistenceService.findEntityByName(entityJson.getName()) != null) {
        	EntityBean entityBase = persistenceService.findEntityByName(entityJson.getName());
            throw new ZupException(String.format("Entidade '%s' já existente, remova %s e adicione novamente ", entityBase.getName(),  entityBase.getId()));
        }

        persistenceService.createEntityOrThrow(entityBean);
        persistenceService.createCollection(entityBean.getName());
        return new EntityFactory(entityPersistence.save(entityBean));
    }

    public Object delete(String id) throws ZupException {
        LOGGER.info("EntityService - delete - entidadeIs {}", id);
        EntityBean entityBean = persistenceService.findEntityByIdOrThrow(id);
        persistenceService.dropEntityByName(entityBean.getName());
        entityPersistence.deleteById(id);
        return new EntityFactory(entityBean);
    }

}
