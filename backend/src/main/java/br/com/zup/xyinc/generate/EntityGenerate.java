package br.com.zup.xyinc.generate;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

import java.io.Serializable;
import java.util.Map;

public class EntityGenerate {

    private EntityGenerate() {
    }
    
    private static final ClassPool pool = ClassPool.getDefault();

    public static Class create(String entityName, Map<String, Class<?>> attributes)
        throws ClassNotFoundException,
            NotFoundException,
            CannotCompileException {

        CtClass cc = pool.getOrNull(entityName);

        if (cc != null) {
            return Class.forName(entityName, false, pool.getClassLoader());
        }

        cc = pool.makeClass(entityName);
        cc.addInterface(resolveCtClass(Serializable.class));

        cc.addField(new CtField(resolveCtClass(String.class), "_id", cc));
        cc.addMethod(createGet(cc, "_id", String.class));
        cc.addMethod(createSet(cc, "_id", String.class));
        
        for (Map.Entry<String, Class<?>> entry : attributes.entrySet()) {
            cc.addField(new CtField(resolveCtClass(entry.getValue()), entry.getKey(), cc));
            cc.addMethod(createGet(cc, entry.getKey(), entry.getValue()));
            cc.addMethod(createSet(cc, entry.getKey(), entry.getValue()));
        }
        
        return cc.toClass();
    }

    private static CtClass resolveCtClass(Class clazz)
        throws NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        return pool.get(clazz.getName());
    }

    private static CtMethod createGet(CtClass declaringClass, String fieldName, Class fieldClass)
        throws CannotCompileException {

        String getterName = createGetName(fieldName);

        StringBuffer sb = new StringBuffer();
        sb.append("public ").append(fieldClass.getName()).append(" ").append(getterName).append("(){")
            .append("return this.").append(fieldName).append(";")
            .append("}");

        return CtMethod.make(sb.toString(), declaringClass);
    }

    private static CtMethod createSet(CtClass declaringClass, String fieldName, Class fieldClass)
        throws CannotCompileException {

        String setterName = createSetName(fieldName);

        StringBuffer sb = new StringBuffer();
        sb.append("public void ").append(setterName).append("(").append(fieldClass.getName()).append(" ").append(fieldName).append(")").append("{")
            .append("this.").append(fieldName).append("=").append(fieldName).append(";")
            .append("}");

        return CtMethod.make(sb.toString(), declaringClass);
    }

    private static String createGetName(String fieldName) {
        return createMethodNameWithPrefix("get", fieldName);
    }

    private static String createSetName(String fieldName) {
        return createMethodNameWithPrefix("set", fieldName);
    }

    private static String createMethodNameWithPrefix(String prefix, String fieldName) {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }


}
