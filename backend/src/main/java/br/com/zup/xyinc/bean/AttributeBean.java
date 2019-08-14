package br.com.zup.xyinc.bean;

import java.io.Serializable;

import br.com.zup.xyinc.type.TypeEntity;

public class AttributeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

    private TypeEntity type;

    private String typeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeEntity getType() {
        return type;
    }

    public void setType(TypeEntity type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AttributeBean attribute = (AttributeBean) o;

        if (name != null ? !name.equals(attribute.name) : attribute.name != null) return false;
        if (type != attribute.type) return false;
        return typeName != null ? typeName.equals(attribute.typeName) : attribute.typeName == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                '}';
    }

}
