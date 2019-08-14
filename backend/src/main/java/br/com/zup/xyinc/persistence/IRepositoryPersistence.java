package br.com.zup.xyinc.persistence;

import java.util.List;
import java.util.Map;

public interface IRepositoryPersistence {

    void createCollection(String name);

    void dropCollection(String name);

    List<Object> findAllRecords(Class clazz, String entityName);

    <T> T  findRecord(Class<T> clazz, String entityName, String id);

    void saveRecord(String entityName, Map<String, Object> attributes);

    void deleteRecord(Object record, String entityName);

}
