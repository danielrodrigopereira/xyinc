package br.com.zup.xyinc.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.zup.xyinc.bean.AttributeBean;
import br.com.zup.xyinc.bean.EntityBean;
import br.com.zup.xyinc.builder.AttributeBuilder;
import br.com.zup.xyinc.builder.EntityBuilder;
import br.com.zup.xyinc.type.TypeEntity;
import br.com.zup.xyinc.util.ZupException;

public class EntityFactory implements Serializable {

    private String id;

    private String name;

    private Map<String, String> attributes;

    public EntityFactory() {

    }

    public EntityFactory(EntityBean entity) {
        id = entity.getId();
        name = entity.getName();
        attributes = new HashMap<>();
        entity.getAttributes().forEach(att -> attributes.put(att.getName(), att.getTypeName()));
    }

    public EntityBean unwrap()
        throws ZupException {
        List<AttributeBean> attList = new ArrayList<>();
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            attList.add(new AttributeBuilder()
                .name(entry.getKey()).type(TypeEntity.getTypeByDescriptionOrThrow(entry.getValue())).typeName(entry.getValue()).build());
        }
        return new EntityBuilder().id(id).name(name).attributes(attList).build();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    
}
