package br.com.zup.xyinc.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.zup.xyinc.bean.EntityBean;
import br.com.zup.xyinc.util.ZupException;

@Service
public class ApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);

    @Autowired
    private PersistenceService persistenceFactory;

    public List<Object> findAll(String entityName) throws ZupException {
        EntityBean entity = persistenceFactory.getEntityByNameOrThrow(entityName);
        LOGGER.info("ApiService - findAall - entidade {}", entity);
        //TODO: Implementar um adapter para data no parttener DD/MM/YYYY
        return persistenceFactory.findAllRecords(entity);
    }

    public Object findById(String entityName, String id) throws ZupException {
        LOGGER.info("ApiService - findById - entidade {} id {}", entityName, id);
        EntityBean entity = persistenceFactory.getEntityByNameOrThrow(entityName);
        //TODO: Implementar um adapter para data no parttener DD/MM/YYYY
        return persistenceFactory.findRecordByIdOrThrow(entity, id);
    }

    public Map<String, Object> create(String entityName, Map<String, Object> attributes) throws ZupException {
        String id = UUID.randomUUID().toString();
        attributes.put("_id", id);
        LOGGER.info("ApiService - create - entidade {} id {}", entityName, id);
        return persistenceFactory.saveRecord(entityName, attributes);
    }

    public Map<String, Object> update(String entityName, String id, Map<String, Object> attributes) throws ZupException {
        attributes.put("_id", id);
        LOGGER.info("ApiService - update - entidade {} id {}", entityName, id);
        EntityBean entity = persistenceFactory.getEntityByNameOrThrow(entityName);
        persistenceFactory.findRecordByIdOrThrow(entity, id);
        return persistenceFactory.saveRecord(entityName, attributes);
    }

    public Object deleteById(String entityName, String id) throws ZupException {
        LOGGER.info("ApiService - deleteById - entidade {} id {}", entityName, id);
        EntityBean entity = persistenceFactory.getEntityByNameOrThrow(entityName);
        Object record = persistenceFactory.findRecordByIdOrThrow(entity, id);
        persistenceFactory.deleteRecord(record, entity.getName());
        return record;
    }
    



}
