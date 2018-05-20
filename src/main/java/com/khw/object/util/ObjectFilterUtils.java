package com.khw.object.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import org.apache.commons.lang3.ClassUtils;

public class ObjectFilterUtils {

    private static final Map<Class<?>, Class<?>> primitiveWrapperTypeMap = new IdentityHashMap<>(8);

    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);
        primitiveWrapperTypeMap.put(Byte.class, byte.class);
        primitiveWrapperTypeMap.put(Character.class, char.class);
        primitiveWrapperTypeMap.put(Double.class, double.class);
        primitiveWrapperTypeMap.put(Float.class, float.class);
        primitiveWrapperTypeMap.put(Integer.class, int.class);
        primitiveWrapperTypeMap.put(Long.class, long.class);
        primitiveWrapperTypeMap.put(Short.class, short.class);
    }

    public static <T> void objectFilter(Object obj, Function<T, T> filter, Class<T> tClass) {
        try {
            if (obj == null) {
                return;
            }
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                // field 접속 가능하도록 수정.
                field.setAccessible(true);

                Object fieldObj = field.get(obj);
                if (fieldObj == null) {
                    continue;
                }

                if (isSimpleValueType(fieldObj.getClass())) {
                    if (field.getType() == tClass || ClassUtils.isAssignable(field.getType(), tClass)) {
                        field.set(obj, filter.apply((T) fieldObj));
                    }
                } else if (fieldObj instanceof List || fieldObj instanceof Object[] || fieldObj.getClass().isArray() || fieldObj instanceof Map) {
                    field.set(obj, typeObjectFilter(fieldObj, filter, tClass));
                } else {
                    objectFilter(fieldObj, filter, tClass);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Object typeObjectFilter(Object object, Function<T, T> filter, Class<T> tClass) {
        if (object == null) {
            return object;
        }
        if (object.getClass() == tClass) {
            object = filter.apply((T) object);
        } else if (object instanceof List) {
            object = listObjectFilter((List) object, filter, tClass);
        } else if (object instanceof Object[]) {
            object = objectArrayObjectFilter((Object[]) object, filter, tClass);
        } else if (object.getClass().isArray()) {
            object = arrayObjectFilter(object, filter, tClass);
        } else if (object instanceof Map) {
            object = mapObjectFilter((Map) object, filter, tClass);
        } else {
            objectFilter(object, filter, tClass);
        }
        return object;
    }

    public static <T> Map mapObjectFilter(Map<Object, Object> object, Function<T, T> filter, Class<T> tClass) {
        if (object == null) {
            return object;
        }
        Map<Object, Object> result = new HashMap<>();
        for (Map.Entry<Object, Object> entry : object.entrySet()) {
            Object valueObj = entry.getValue();
            if (valueObj == null) {
                continue;
            }
            if (valueObj.getClass() == tClass) {
                result.put(entry.getKey(), filter.apply((T) valueObj));
            } else {
                result.put(entry.getKey(), typeObjectFilter(valueObj, filter, tClass));
            }
        }
        return result;
    }

    public static <T> Object arrayObjectFilter(Object object, Function<T, T> filter, Class<T> tClass) {
        if (object == null) {
            return object;
        }
        int length = Array.getLength(object);
        if (length == 0) {
            return object;
        }
        Object subValue = Array.get(object, 0);
        boolean isFilterObject = ClassUtils.isAssignable(subValue.getClass(), tClass);
        if (isFilterObject) {
            for (int i = 0; i < length; i++) {
                Array.set(object, i, filter.apply((T) Array.get(object, i)));
            }
        }
        return object;
    }

    public static <T> Object[] objectArrayObjectFilter(Object[] object, Function<T, T> filter, Class<T> tClass) {
        if (object == null || object.length == 0) {
            return object;
        }
        for (int i = 0; i < object.length; i++) {
            Object valueObj = object[i];
            if (valueObj == null) {
                continue;
            }
            if (valueObj.getClass() == tClass) {
                object[i] = filter.apply((T) valueObj);
            } else {
                object[i] = typeObjectFilter(valueObj, filter, tClass);
            }
        }
        return object;
    }

    public static <T> List listObjectFilter(List object, Function<T, T> filter, Class<T> tClass) {
        if (object == null || object.size() == 0) {
            return object;
        }
        int length = object.size();
        for (int i = 0; i < length; i++) {
            Object valueObj = object.get(i);
            if (valueObj == null) {
                continue;
            }
            if (valueObj.getClass() == tClass) {
                object.set(i, filter.apply((T) valueObj));
            } else if (isSimpleValueType(valueObj.getClass()) == false) {
                object.set(i, typeObjectFilter(valueObj, filter, tClass));
            } else {
                object.set(i, valueObj);
            }
        }
        return object;
    }

    private static boolean isSimpleValueType(Class<?> clazz) {
        return (isPrimitiveOrWrapper(clazz) ||
            Enum.class.isAssignableFrom(clazz) ||
            CharSequence.class.isAssignableFrom(clazz) ||
            Number.class.isAssignableFrom(clazz) ||
            Date.class.isAssignableFrom(clazz) ||
            URI.class == clazz || URL.class == clazz ||
            Locale.class == clazz || Class.class == clazz);
    }

    private static boolean isPrimitiveOrWrapper(Class<?> clazz) {
        return (clazz.isPrimitive() || isPrimitiveWrapper(clazz));
    }

    private static boolean isPrimitiveWrapper(Class<?> clazz) {
        return primitiveWrapperTypeMap.containsKey(clazz);
    }

}
