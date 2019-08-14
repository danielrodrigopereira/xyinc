package br.com.zup.xyinc.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.com.zup.xyinc.type.TypeEntity;
import br.com.zup.xyinc.util.ZupException;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Document(collection = "entity")
public class EntityBean implements Serializable {

    private static final long serialVersionUID = 8327661986357448096L;

    @Id
    private String id;

    private String name;

    private List<AttributeBean> attributes;

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

    public List<AttributeBean> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeBean> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBean entity = (EntityBean) o;

        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        return attributes != null ? attributes.equals(entity.attributes) : entity.attributes == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }
    
    public void validateAttributesOrThrow(Map<String, Object> attributes) throws ZupException {
        StringBuilder sb = new StringBuilder();
        for (AttributeBean attribute : this.attributes) {
            if (attribute.getType().equals(TypeEntity.DATE)) {
                try {
                    new SimpleDateFormat("dd/MM/yyyy").parse(attributes.get(attribute.getName()).toString());
                } catch (ParseException e) {
                    sb.append(String.format(" %s.", attribute.getName()));
                }
            } else {
                Class clazz1 = attribute.getType().getTypeObject();
                Object classAux = attributes.get(attribute.getName());
                if (classAux == null) {
                    sb.append(String.format(" %s.", attribute.getName()));
                }else {
	                Class clazz2 = classAux.getClass();
	                if (!clazz1.isAssignableFrom(clazz2)) {
	                    sb.append(String.format(" %s.", attribute.getName()));
	                }
                }
            }
        }
        if (!sb.toString().isEmpty()) {
            throw new ZupException(String.format("Tipo: %s não suportado", sb.toString()));
        }
    }	

}
