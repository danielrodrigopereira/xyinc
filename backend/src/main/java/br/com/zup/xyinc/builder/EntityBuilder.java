package br.com.zup.xyinc.builder;

import java.util.List;

import br.com.zup.xyinc.bean.AttributeBean;
import br.com.zup.xyinc.bean.EntityBean;

public class EntityBuilder {

    private EntityBean entity;

    public EntityBuilder() {
    	entity = new EntityBean();
    }

    public EntityBean build() {
        return entity;
    }

    public EntityBuilder id(String id) {
    	entity.setId(id);
        return this;
    }

    public EntityBuilder name(String name) {
    	entity.setName(name);
        return this;
    }

    public EntityBuilder attributes(List<AttributeBean> attributes) {
    	entity.setAttributes(attributes);
        return this;
    }

}
