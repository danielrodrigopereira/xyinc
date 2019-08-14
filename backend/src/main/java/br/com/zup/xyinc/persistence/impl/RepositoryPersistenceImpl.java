
package br.com.zup.xyinc.persistence.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import br.com.zup.xyinc.persistence.IRepositoryPersistence;

@Repository
public class RepositoryPersistenceImpl implements IRepositoryPersistence {

    @Autowired
    private MongoTemplate mongoTemplate;
    
    @Override
    public void createCollection(String name) {
    	mongoTemplate.createCollection(name);
    }

    @Override
    public void dropCollection(String name) {
        mongoTemplate.dropCollection(name);
    }

    @Override
    public List<Object> findAllRecords(Class clazz, String entity) {
        return mongoTemplate.findAll(clazz, entity);
    }
    
    @Override
    public <T> T findRecord(Class<T> clazz, String entity, String id) {
        return mongoTemplate.findById(id, clazz, entity);
    }

    @Override
    public void saveRecord(String entity, Map<String, Object> attributes) {
        mongoTemplate.save(attributes, entity);
    }

    @Override
    public void deleteRecord(Object record, String entity) {
        mongoTemplate.remove(record, entity);
    }

}
