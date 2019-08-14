package br.com.zup.xyinc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.xyinc.bean.EntityBean;
import br.com.zup.xyinc.generate.EntityGenerate;
import br.com.zup.xyinc.persistence.IEntityPersistence;
import br.com.zup.xyinc.persistence.IRepositoryPersistence;
import br.com.zup.xyinc.util.ZupException;

@Service
public class PersistenceService {

    private static final String CLASS_PATH = "br.com.zup.xyinc.";

    @Autowired
    private IRepositoryPersistence repositoryPersistence;
    
    @Autowired
    private IEntityPersistence entityPersistence;

    private Map<String, Class<?>> dynamicClasses = new HashMap<>();

    public void createEntityOrThrow(EntityBean entity) throws ZupException {
        Map<String, Class<?>> fields = new HashMap<>();
        entity.getAttributes().forEach(att -> fields.put(att.getName(), att.getType().getTypeObject()));
        try {
            String className = CLASS_PATH + entity.getName();
            Class clazz = EntityGenerate.create(className, fields);
            dynamicClasses.put(className, clazz);
        } catch (Exception e) {
            throw new ZupException("Entidade '%s' não está criado. %s", entity.getName(), e.getMessage());
        }
    }

    public void createCollection(String name) {
        repositoryPersistence.createCollection(name);
    }

    public void dropEntityByName(String name) {
        repositoryPersistence.dropCollection(name);
    }

    public List<Object> findAllRecords(EntityBean entity) throws ZupException {
        return repositoryPersistence.findAllRecords(getDynamicClass(entity), entity.getName());
    }

    public Object findRecord(EntityBean entity, String id) throws ZupException {
        return repositoryPersistence.findRecord(getDynamicClass(entity), entity.getName(), id);
    }

    public void saveRecord(EntityBean entity, Map<String, Object> attributes) {
    	repositoryPersistence.saveRecord(entity.getName(), attributes);
    }

    public void deleteRecord(Object record, String entity) {
    	repositoryPersistence.deleteRecord(record, entity);
    }

    private Class<?> getDynamicClass(EntityBean entity)
        throws ZupException {
        String className = CLASS_PATH + entity.getName();
        if (!dynamicClasses.containsKey(className)) {
        	createEntityOrThrow(entity);
        }
        return dynamicClasses.get(className);
    }
    
    public EntityBean getEntityByNameOrThrow(String entityName) throws ZupException {
        EntityBean entity = findEntityByName(entityName);
        if (entity == null) {
            throw new ZupException("Entidade '%s' não integrado", entityName);
        }
        return entity;
    }
    
    public Object findRecordByIdOrThrow(EntityBean entity, String id) throws ZupException {
        Object o = findRecord(entity, id);
        if (o == null) {
            throw new ZupException("Entidade '%s', registro '%s' não encontrado", entity.getName(), id);        	
        }
        return o;
    }
    
    public EntityBean findEntityByName(String name) {
        return entityPersistence.findByName(name);
    }

    public Map<String, Object> saveRecord(String entityName, Map<String, Object> attributes) throws ZupException {
        EntityBean entity = getEntityByNameOrThrow(entityName);
        entity.validateAttributesOrThrow(attributes);
        saveRecord(entity, attributes);
        return attributes;
    }

    public EntityBean findEntityByIdOrThrow(String id) throws ZupException {
        Optional<EntityBean> entity = entityPersistence.findById(id);
        if (entity == null || entity.isEmpty()) {
            throw new ZupException("Entidade '%s' não encontrado", id);
        }
        return entity.get();
    }

}
