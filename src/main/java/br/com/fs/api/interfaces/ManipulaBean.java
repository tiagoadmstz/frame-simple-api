/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fs.api.interfaces;

import br.com.fs.api.annotations.MapSqlField;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tiago.teixeira
 * @param <T>
 */
public abstract class ManipulaBean<T> implements Serializable {

    private static final long serialVersionUID = 5442956909521649571L;

    public synchronized void clear() {
        try {
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("serialVersionUID") && !field.getName().equals("version") && !field.getName().contains("this")) {
                    if (field.getType() != boolean.class && field.getType() != Boolean.class) {
                        field.set(this, null);
                    } else if (field.getType() == boolean.class && field.getType() == Boolean.class) {
                        field.setBoolean(this, false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void copiar(T object) {
        try {
            if (object != null) {
                for (Field field : getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if (!field.getName().equals("serialVersionUID") && !field.getName().contains("this")) {
                        field.set(this, field.get(object));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized T clonar() {
        try {
            T object = (T) getClass().getConstructor().newInstance();
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (!field.getName().equals("serialVersionUID") && !field.getName().contains("this")) {
                    Field f = object.getClass().getDeclaredField(field.getName());
                    f.setAccessible(true);
                    f.set(object, field.get(this));
                }
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void convert(Object object) {
        try {
            List<Field> fields = new ArrayList();
            if (getClass().getSuperclass() != null) {
                fields.addAll(Arrays.asList(getClass().getSuperclass().getDeclaredFields()));
            }
            fields.addAll(Arrays.asList(getClass().getDeclaredFields()));
            for (Field field : fields) {
                field.setAccessible(true);
                Field tf = null;
                try {
                    tf = object.getClass().getDeclaredField(field.getName());
                } catch (Exception e) {
                    continue;
                }
                if (tf != null) {
                    tf.setAccessible(true);
                    if (tf.get(object) != null && !tf.getName().equals("serialVersionUID") && !field.getName().contains("this")) {
                        field.set(this, tf.get(object));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printValues() {
        try {
            List<Field> fields = new ArrayList();
            if (getClass().getSuperclass() != null) {
                fields.addAll(Arrays.asList(getClass().getSuperclass().getDeclaredFields()));
            }
            fields.addAll(Arrays.asList(getClass().getDeclaredFields()));
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(this) != null && !field.getName().equals("serialVersionUID") && !field.getName().contains("this")) {
                    System.out.println(field.getName() + " = " + field.get(this));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isNull() {
        try {
            boolean resultado = true;
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(this) != null && !field.getName().equals("serialVersionUID") && !field.getName().contains("this")) {
                    if (field.getType() == List.class) {
                        if (((List) field.get(this)).isEmpty()) {
                            continue;
                        }
                    } else if (field.getType() == String.class) {
                        if (((String) field.get(this)).equals("")) {
                            continue;
                        }
                    }
                    return false;
                }
            }
            return resultado;
        } catch (Exception e) {
            return true;
        }
    }

    public T getInstance() {
        try {
            for (Constructor construtor : getClass().getConstructors()) {
                if (construtor.getParameterCount() == 0) {
                    return (T) construtor.newInstance();
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Object> getParamList() {
        try {
            Map<String, Object> resultado = new HashMap();
            String prefix = "param";
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(this) != null && !field.getName().equals("serialVersionUID") && !field.getName().contains("this")) {
                    if (!field.get(this).equals("")) {
                        if (field.getType() == String.class) {
                            String valor = ""
                                    .concat("%")
                                    .concat(field.get(this).toString().toLowerCase().replaceAll("\\s+", "%"))
                                    .concat("%");
                            resultado.put(prefix.concat(field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase())), valor);
                        } else {
                            resultado.put(prefix.concat(field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase())), field.get(this));
                        }
                    }
                }
            }
            return resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public String getSQL_Select() {
        try {
            String prefix = getClass().getSimpleName().substring(0, 2).toLowerCase();
            String resultado = "SELECT "
                    .concat(prefix).concat(" FROM ")
                    .concat(getClass().getSimpleName())
                    .concat(" AS ").concat(prefix);
            return resultado;
        } catch (Exception e) {
            return "";
        }
    }

    public String getSQL_Where(String prefix) {
        try {
            String resultado = "";
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(this) != null && !field.getName().equals("serialVersionUID") && !field.getName().contains("this")) {
                    if (!field.get(this).equals("")) {
                        resultado = getMapSqlField(resultado, field, prefix);
                    }
                }
            }
            return resultado;
        } catch (Exception e) {
            return "";
        }
    }

    public String getSQL() {
        try {
            return this.getSQL_Select().concat(" ").concat(this.getSQL_Where(null));
        } catch (Exception e) {
            return "";
        }
    }

    private String getMapSqlField(String resultado, Field field, String prefix) {
        try {
            if (field.isAnnotationPresent(MapSqlField.class)) {
                MapSqlField map = field.getAnnotation(MapSqlField.class);
                if (!map.ignore()) {
                    return mountWhere(resultado, field, prefix, map);
                } else {
                    return resultado;
                }
            } else {
                return mountWhere(resultado, field, prefix, null);
            }
        } catch (Exception e) {
            return resultado;
        }
    }

    private String mountWhere(String resultado, Field field, String prefix, MapSqlField map) {
        try {
            String p = "";
            if (map.as_prefix().equals("")) {
                p = prefix == null ? getClass().getSimpleName().substring(0, 2).concat(".").toLowerCase() : prefix.concat(".");
            } else {
                p = map.as_prefix().concat(".");
            }
            String param = "param";

            resultado = mountPrefixWhere(resultado, (map != null ? p.concat(map.mapeamento()) : p.concat(field.getName())), field.getType());

            if (map != null) {
                switch (map.condition()) {
                    case "=":
                    case ">":
                    case "<":
                    case ">=":
                    case "<=":
                    case "<>":
                    case "!=":
                        resultado = resultado.concat(" ".concat(map.condition()).concat(" ").concat(":".concat(param.concat(field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase())))));
                        break;
                    case "between":
                        resultado = resultado.concat(" BETWEEN ".concat(":".concat(param.concat(field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase())))));
                        resultado = resultado.concat(" AND ".concat(":".concat(param.concat(map.between_partiner().replaceFirst(map.between_partiner().substring(0, 1), map.between_partiner().substring(0, 1).toUpperCase())))));
                        break;
                    case "like":
                        resultado = resultado.replace(p.concat(field.getName()), "LOWER(".concat(p.concat(field.getName())).concat(")"));
                        resultado = resultado.concat(" LIKE :".concat(param.concat(field.getName().replaceFirst(field.getName().substring(0, 1), field.getName().substring(0, 1).toUpperCase()))));
                        break;
                    default:
                        resultado = getDefaultWhereClausule(resultado, field.getName(), field.getType());
                }
            } else {
                resultado = getDefaultWhereClausule(resultado, field.getName(), field.getType());
            }
            return resultado;
        } catch (Exception e) {
            return resultado;
        }
    }

    private String getDefaultWhereClausule(String resultado, String campo, Class type) {
        try {
            if (type == String.class) {
                resultado = resultado.concat(" LIKE :".concat("param".concat(campo.replaceFirst(campo.substring(0, 1), campo.substring(0, 1).toUpperCase()))));
            } else {
                resultado = resultado.concat(" = :".concat("param".concat(campo.replaceFirst(campo.substring(0, 1), campo.substring(0, 1).toUpperCase()))));
            }
            return resultado;
        } catch (Exception e) {
            return resultado;
        }
    }

    private String mountPrefixWhere(String resultado, String campo, Class type) {
        try {
            resultado = resultado.equals("") ? resultado.concat("WHERE ").concat(campo) : resultado.concat(" AND ").concat(campo);
            if (type == String.class) {
                resultado = resultado.replace(campo, "LOWER(".concat(campo).concat(")"));
            }
            return resultado;
        } catch (Exception e) {
            return resultado;
        }
    }

}
