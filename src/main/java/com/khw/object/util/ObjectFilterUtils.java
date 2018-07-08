package com.khw.object.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

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

    /**
     * 사용자 정의 객체를 입력받아서 해당 객체의 내부 기본 값들을 돌면서 value 의 값이 tClass 와 일치하는 타입일 경우 filter 함수를 실행한 결과로 대치한다.
     *
     * @param obj - filter 를 처리하려고 하는 사용자 정의 객체.
     * @param filter - tClass 에 해당하는 value 를 처리할 filter - Function 의 구현체.
     * @param tClass - value 값들 중 특정 타입만 filter 를 처리하려고 할 때의 value 타입.
     */
    public static <T> Object objectFilter(Object obj, Function<T, T> filter, Class<T> tClass) {
        try {
            if (obj == null) {
                return obj;
            }
            if (isAssignableFrom(obj.getClass(), tClass)) {
                return filter.apply((T) obj);
            }

            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                // field 접속 가능하도록 수정.
                field.setAccessible(true);

                Object fieldObj = field.get(obj);
                if (isSimpleValueType(field.getType())) {
                    if (field.getType() == tClass || isAssignableFrom(field.getType(), tClass)) {
                        field.set(obj, filter.apply((T) field.get(obj)));
                    }
                } else if (isAssignableFrom(field.getType(), Collection.class) || isAssignableFrom(field.getType(), Object[].class) || isAssignableFrom(
                    field.getType(), Map.class) || field.getType().isArray()) {
                    field.set(obj, typeObjectFilter(fieldObj, filter, tClass));
                } else {
                    field.set(obj, objectFilter(fieldObj, filter, tClass));
                }
            }
            return obj;
        } catch (Exception e) {
            throw new ObjectFilterException(e);
        }
    }

    /**
     * 사용자 정의 객체를 입력하면 해당 객체를 tClass 타입과 일치하는 value 를 filter 처리하여 반환한다. recursive 처리.
     *
     * @param object - filter 를 처리하려고 하는 사용자 정의 객체.
     * @param filter - tClass 에 해당하는 value 를 처리할 filter - Function 의 구현체.
     * @param tClass - value 값들 중 특정 타입만 filter 를 처리하려고 할 때의 value 타입.
     */
    public static <T> Object typeObjectFilter(Object object, Function<T, T> filter, Class<T> tClass) {
        try {
            if (object == null) {
                return object;
            }
            // object 가 처리를 원하는 클래스일 경우.
            if (isAssignableFrom(object.getClass(), tClass)) {
                object = filter.apply((T) object);
            }
            // object 가 List 타입일 경우.
            else if (isAssignableFrom(object.getClass(), List.class)) {
                object = listObjectFilter((List) object, filter, tClass);
            }
            // object 가 Set 타입일 경우.
            else if (isAssignableFrom(object.getClass(), Set.class)) {
                object = setObjectFilter((Set) object, filter, tClass);
            }
            // object 가 wrapper class 의 배열일 경우.
            else if (isAssignableFrom(object.getClass(), Object[].class)) {
                object = objectArrayObjectFilter((Object[]) object, filter, tClass);
            }
            // object 가 primitive type 의 배열인 경우.
            else if (object.getClass().isArray()) {
                object = arrayObjectFilter(object, filter, tClass);
            }
            // object 가 Map 형식일 경우.
            else if (isAssignableFrom(object.getClass(), Map.class)) {
                object = mapObjectFilter((Map) object, filter, tClass);
            }
            // 어떤 형식에도 표함되지 않을 경우에는 사용자 정의 객체일 수 있기 때문에 다시 호출.
            else {
                objectFilter(object, filter, tClass);
            }
            return object;
        } catch (Exception e) {
            throw new ObjectFilterException(e);
        }
    }

    /**
     * Set 의 구현체의 value 에 대한 필터를 처리할 경우 사용. HashSet 의 경우에는 순서가 보장되지 않기 때문에 LinkedHashSet 으로 만들어서 순서를 보장하는 Set 을 반환한다.
     */
    private static <T> Object setObjectFilter(Set object, Function<T, T> filter, Class<T> tClass) {
        try {
            if (object == null || object.size() == 0) {
                return object;
            }
            Object[] objectArr = object.toArray();
            Set result = new LinkedHashSet();
            for (int i = 0; i < objectArr.length; i++) {
                Object valueObj = objectArr[i];
                if (valueObj == null) {
                    result.add(null);
                    continue;
                }

                if (isAssignableFrom(valueObj.getClass(), tClass)) {
                    result.add(filter.apply((T) valueObj));
                } else if (isSimpleValueType(valueObj.getClass())) {
                    result.add(valueObj);
                } else {
                    result.add(typeObjectFilter(valueObj, filter, tClass));
                }
            }
            return result;
        } catch (Exception e) {
            throw new ObjectFilterException(e);
        }
    }

    /**
     * Map 의 구현체의 value 에 대한 필터를 처리할 경우 사용.
     */
    public static <T> Map mapObjectFilter(Map<Object, Object> object, Function<T, T> filter, Class<T> tClass) {
        try {
            if (object == null || object.size() == 0) {
                return object;
            }
            Map<Object, Object> result = new HashMap<>();
            for (Map.Entry<Object, Object> entry : object.entrySet()) {
                Object valueObj = entry.getValue();
                if (valueObj == null) {
                    continue;
                }
                // value 의 타입이 필터를 처리하려는 타입과 같을 경우.
                if (isAssignableFrom(valueObj.getClass(), tClass)) {
                    result.put(entry.getKey(), filter.apply((T) valueObj));
                }
                // value 가 심플 타입일 경우 입력.
                else if (isSimpleValueType(valueObj.getClass())) {
                    result.put(entry.getKey(), valueObj);
                }
                // value 의 타입이 필터를 처리하려는 타입과 다를 경우 상위 호출.
                else {
                    result.put(entry.getKey(), typeObjectFilter(valueObj, filter, tClass));
                }
            }
            return result;
        } catch (Exception e) {
            throw new ObjectFilterException(e);
        }
    }

    /**
     * primitive type array 에 대한 필터를 처리할 경우 사용.
     */
    public static <T> Object arrayObjectFilter(Object object, Function<T, T> filter, Class<T> tClass) {
        try {
            if (object == null || Array.getLength(object) == 0) {
                return object;
            }
            int length = Array.getLength(object);

            // primitive array 는 값이 null 이 올 수 없기 때문에 첫번째 인자를 통해 타입 체크를 한번만 한다.
            Object value = Array.get(object, 0);
            if (isAssignableFrom(value.getClass(), tClass)) {
                for (int i = 0; i < length; i++) {
                    Array.set(object, i, filter.apply((T) Array.get(object, i)));
                }
            }
            return object;
        } catch (Exception e) {
            throw new ObjectFilterException(e);
        }
    }

    /**
     * wrapper class array 에 대한 필터 처리.
     */
    public static <T> Object[] objectArrayObjectFilter(Object[] object, Function<T, T> filter, Class<T> tClass) {
        try {
            if (object == null || object.length == 0) {
                return object;
            }
            for (int i = 0; i < object.length; i++) {
                Object valueObj = object[i];
                if (valueObj == null) {
                    continue;
                }
                // 인자의 타입이 필터 처리할 타입과 같을 경우.
                if (isAssignableFrom(valueObj.getClass(), tClass)) {
                    object[i] = filter.apply((T) valueObj);
                }
                // 인자가 심플 타입일 경우 바로 입력.
                else if (isSimpleValueType(valueObj.getClass())) {
                    object[i] = valueObj;
                }
                // 필터 처리할 타입과 다를 경우에는 내부에 어떤 타입이라도 올 수 있기 때문에 typeObjectFilter 를 호출한 결과를 삽입.
                else {
                    object[i] = typeObjectFilter(valueObj, filter, tClass);
                }
            }
            return object;
        } catch (Exception e) {
            throw new ObjectFilterException(e);
        }
    }

    /**
     * List 구현체의 필터 처리.
     */
    public static <T> List listObjectFilter(List object, Function<T, T> filter, Class<T> tClass) {
        try {
            if (object == null || object.size() == 0) {
                return object;
            }
            int length = object.size();
            for (int i = 0; i < length; i++) {
                Object valueObj = object.get(i);
                if (valueObj == null) {
                    continue;
                }
                // 값이 처리할 타입과 동일한 경우.
                if (isAssignableFrom(valueObj.getClass(), tClass)) {
                    object.set(i, filter.apply((T) valueObj));
                }
                // 값이 심플한 타입의 값이 아닐 경우에는 여러가지 타입이 올 수 있기 때문에 다시 상위 호출.
                else if (isSimpleValueType(valueObj.getClass())) {
                    object.set(i, valueObj);
                }
                // 값이 심플한 형태일 경우이므로 필터 대상 타입과 일치하지 않으면 그냥 입력 처리.
                else {
                    object.set(i, typeObjectFilter(valueObj, filter, tClass));
                }
            }
            return object;
        } catch (Exception e) {
            throw new ObjectFilterException(e);
        }
    }

    /**
     * tClass 가 심플한 Value type 인지 확인. String, Integer, Long 등등의 기본 자료형인지 확인.
     */
    private static boolean isSimpleValueType(Class<?> tClass) {
        return primitiveWrapperTypeMap.containsKey(tClass);
    }

    /**
     * obj 가 tClass 로 타입 캐스트가 가능한지 확인하여 결과를 반환한다.
     */
    private static boolean isAssignableFrom(Class<?> objType, Class<?> tClass) {
        return tClass.isAssignableFrom(objType);
    }
}
