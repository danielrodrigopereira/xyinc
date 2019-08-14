package br.com.zup.xyinc.type;

import java.util.Date;

import br.com.zup.xyinc.util.StringUtil;
import br.com.zup.xyinc.util.ZupException;

public enum TypeEntity {

    STRING(String.class, "TEXTO"),
    INTEGER(Integer.class, "NUMERO"),
    BOOLEAN(Boolean.class, "ATIVO"),
    DATE(Date.class, "DATA"),
    DOUBLE(Double.class, "VALOR");
	
    private Class type;
    private String description;

    TypeEntity(Class type, String description) {
        this.type = type;
        this.description = description;
    }

    public Class getTypeObject() {
        return type;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static TypeEntity getTypeByDescriptionOrThrow(String label) throws ZupException{
    	TypeEntity typeEntity = null;
    	if (!StringUtil.isEmpty(label)){
            for(TypeEntity item : TypeEntity.values()){
                if (item.getDescription().equals(label.toUpperCase())){
                	typeEntity = item;
                    break;
                }
            }
        }
    	if (typeEntity == null) {
    		throw new ZupException("Tipo '%s' não suportado, selecione um dos tipos: %s", label, typeSupport());
    	}
    	return typeEntity;
    }

    public static String typeSupport() {
    	StringBuilder retorno = new StringBuilder();
    	retorno.append("Tipos suportados {\n");
        for(TypeEntity item : TypeEntity.values()){
        	retorno.append(item.getDescription()).append("-(").append(item.getTypeObject().getSimpleName()).append("),\n");
        }
    	return retorno.append("}").toString();
    }
    
}
