package br.com.zup.xyinc.builder;

import br.com.zup.xyinc.bean.AttributeBean;
import br.com.zup.xyinc.type.TypeEntity;

public class AttributeBuilder {

    private AttributeBean attribute;

    public AttributeBuilder() {
        attribute = new AttributeBean();
    }

    public AttributeBean build() {
        return attribute;
    }

    public AttributeBuilder name(String name) {
        attribute.setName(name);
        return this;
    }

    public AttributeBuilder type(TypeEntity type) {
        attribute.setType(type);
        return this;
    }

    public AttributeBuilder typeName(String typeName) {
        attribute.setTypeName(typeName);
        return this;
    }

}
