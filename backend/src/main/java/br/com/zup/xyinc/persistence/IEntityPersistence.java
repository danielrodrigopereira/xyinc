package br.com.zup.xyinc.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.zup.xyinc.bean.EntityBean;

public interface IEntityPersistence extends MongoRepository<EntityBean, String> {

    EntityBean findByName(String name);

}

