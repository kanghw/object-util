package com.khw.object.util.model;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class TestParam {
    /**
     * default wrapper type
     */
    private String string;
    private Boolean aBoolean;
    private Integer integer;
    private Long aLong;
    private Short aShort;
    private Float aFloat;
    private Double aDouble;
    private Character character;
    private Byte aByte;

    /**
     * default primitive type
     */
    private boolean aBooleanPrimitive;
    private int anIntPrimitive;
    private long aLongPrimitive;
    private short aShortPrimitive;
    private float aFloatPrimitive;
    private double aDoublePrimitive;
    private char aCharPrimitive;
    private byte aBytePrimitive;

    /**
     * default primitive array type
     */
    private boolean[] booleans;
    private int[] ints;
    private long[] longs;
    private short[] shorts;
    private float[] floats;
    private double[] doubles;
    private char[] chars;
    private byte[] bytes;

    /**
     * default wrapper array type
     */
    private String[] stringsObjArr;
    private Boolean[] booleansObjArr;
    private Integer[] integersObjArr;
    private Long[] longsObjArr;
    private Short[] shortsObjArr;
    private Float[] floatsObjArr;
    private Double[] doublesObjArr;
    private Character[] charactersObjArr;
    private Byte[] bytesObjArr;

    /**
     * default wrapper List type
     */
    private List<String> stringList;
    private List<Boolean> booleanList;
    private List<Integer> integerList;
    private List<Long> longList;
    private List<Short> shortList;
    private List<Float> floatList;
    private List<Double> doubleList;
    private List<Character> characterList;
    private List<Byte> byteList;

    /**
     * default wrapper Map type
     */
    private Map<String, String> stringMap;
    private Map<String, Boolean> booleanMap;
    private Map<String, Integer> integerMap;
    private Map<String, Long> longMap;
    private Map<String, Short> shortMap;
    private Map<String, Float> floatMap;
    private Map<String, Double> doubleMap;
    private Map<String, Character> characterMap;
    private Map<String, Byte> byteMap;


    /**
     * primitive array List
     */
    private List<boolean[]> booleansList;
    private List<int[]> intsList;
    private List<long[]> longsList;
    private List<short[]> shortsList;
    private List<float[]> floatsList;
    private List<double[]> doublesList;
    private List<char[]> charactersList;
    private List<byte[]> bytesList;

    /**
     * wrapper array List
     */
    private List<String[]> stringsObjArrList;
    private List<Boolean[]> booleansObjArrList;
    private List<Integer[]> integersObjArrList;
    private List<Long[]> longsObjArrList;
    private List<Short[]> shortsObjArrList;
    private List<Float[]> floatsObjArrList;
    private List<Double[]> doublesObjArrList;
    private List<Character[]> charactersObjArrList;
    private List<Byte[]> bytesObjArrList;

    /**
     * wrapper Map List
     */
    private List<Map<String, String>> stringMapList;
    private List<Map<String, Boolean>> booleanMapList;
    private List<Map<String, Integer>> integerMapList;
    private List<Map<String, Long>> longMapList;
    private List<Map<String, Short>> shortMapList;
    private List<Map<String, Float>> floatMapList;
    private List<Map<String, Double>> doubleMapList;
    private List<Map<String, Character>> characterMapList;
    private List<Map<String, Byte>> byteMapList;

    /**
     * wrapper array Map
     */
    private Map<String, String[]> stringsObjArrMap;
    private Map<String, Boolean[]> booleansObjArrMap;
    private Map<String, Integer[]> integersObjArrMap;
    private Map<String, Long[]> longsObjArrMap;
    private Map<String, Short[]> shortsObjArrMap;
    private Map<String, Float[]> floatsObjArrMap;
    private Map<String, Double[]> doublesObjArrMap;
    private Map<String, Character[]> charactersObjArrMap;
    private Map<String, Byte[]> bytesObjArrMap;

    /**
     * wrapper array Map
     */
    private Map<String, boolean[]> booleanPrimitiveArrMap;
    private Map<String, int[]> intPrimitiveArrMap;
    private Map<String, long[]> longPrimitiveArrMap;
    private Map<String, short[]> shortPrimitiveArrMap;
    private Map<String, float[]> floatPrimitiveArrMap;
    private Map<String, double[]> doublePrimitiveArrMap;
    private Map<String, char[]> charPrimitiveArrMap;
    private Map<String, byte[]> bytePrimitiveArrMap;

    /**
     * wrapper List Map
     */
    private Map<String, List<String>> stringListMap;
    private Map<String, List<Boolean>> booleanListMap;
    private Map<String, List<Integer>> integerListMap;
    private Map<String, List<Long>> longListMap;
    private Map<String, List<Short>> shortListMap;
    private Map<String, List<Float>> floatListMap;
    private Map<String, List<Double>> doubleListMap;
    private Map<String, List<Character>> characterListMap;
    private Map<String, List<Byte>> byteListMap;

    /**
     * object value type
     */
    private TestParam subObject;
    private List<TestParam> subObjectList;
    private Map<String, TestParam> subObjectMap;
    private TestParam[] subObjects;

}
