package com.khw.object.util;

import com.khw.object.util.model.TestParam;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestUtils {

    public TestParam getTestParam(String prefix, String suffix) {
        TestParam testParam = new TestParam();

        testParam.setString(prefix + " string value " + suffix);
        testParam.setABoolean(false);
        testParam.setInteger(1234);
        testParam.setALong(1234L);
        testParam.setAShort((short) 3214);
        testParam.setAFloat(3.23f);
        testParam.setADouble(34.4342);
        testParam.setCharacter('d');
        testParam.setAByte(new Byte("s"));

        testParam.setABooleanPrimitive(false);
        testParam.setAnIntPrimitive(4321);
        testParam.setALongPrimitive(4321);
        testParam.setAShortPrimitive((short) 2324);
        testParam.setAFloatPrimitive(32.33f);
        testParam.setADoublePrimitive(2234.433);
        testParam.setACharPrimitive('p');
        testParam.setABytePrimitive((byte) 22);

        testParam.setBytes(new byte[]{22, 44, 21, 55});
        testParam.setInts(new int[]{432, 5454, 2342, 4243});
        testParam.setLongs(new long[]{34242, 423423545, 24324, 32423, 324423});
        testParam.setShorts(new short[]{243, 234, 55, 33, 212});
        testParam.setFloats(new float[]{342.34f, 3543.453f, 23432.5445f});
        testParam.setDoubles(new double[]{123.34, 23423.112, 321.434, 5423.234});
        testParam.setChars(new char[]{'g', 'e', 'c', 't', 'h'});
        testParam.setBytes(new byte[]{123, 22, 44, 55, 66, 77, 99});

        testParam.setStringsObjArr(new String[]{prefix + " string object array " + suffix, null, "", prefix + "string object array value4" + suffix});
        testParam.setBooleansObjArr(new Boolean[]{false, true, true, false, null, true});
        testParam.setIntegersObjArr(new Integer[]{342, 545, 546, 232, 5643, 5363, null, 24200});
        testParam.setLongsObjArr(new Long[]{23423L, 565332L, 6534L, 25425L, 34523L, null, 453664L});
        testParam.setShortsObjArr(new Short[]{1231, 332, 545, 666, 234, null, 2367, 0});
        testParam.setFloatsObjArr(new Float[]{324.123F, 23426.324F, 653.92F, null, 2049.454F});
        testParam.setDoublesObjArr(new Double[]{242.324, 6433.22, 9989.994, null, 29860.22, 2394293D});
        testParam.setCharactersObjArr(new Character[]{'d', 'e', 'g', null, 'g'});
        testParam.setBytesObjArr(new Byte[]{'c', 'g', 'a', 'h', null, 't'});

        testParam.setStringList(Arrays
            .asList(prefix + " string list value 1" + suffix, prefix + "string value 2" + suffix, prefix + " string list value 3" + suffix, null, "",
                prefix + " string values " + suffix));
        testParam.setBooleanList(Arrays.asList(false, true, false, true, false, null, false, false));
        testParam.setIntegerList(Arrays.asList(234, 543, 64334, 6576, 233, null, 8989));
        testParam.setLongList(Arrays.asList(234L, 345L, 565L, 709L, null, 9923L));
        testParam.setShortList(Arrays.asList((short) 2342, null, (short) 93248));
        testParam.setFloatList(Arrays.asList(2343.34F, 2423.453F, 5345.545F, null, 242.65F));
        testParam.setDoubleList(Arrays.asList(342D, 23423D, null, 324423.324, 39824.934, 0D));
        testParam.setCharacterList(Arrays.asList('y', 'y', 'c', '4', null, '0'));
        testParam.setByteList(Arrays.asList((byte) 33, (byte) 34, (byte) 99, null, (byte) 85));

        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("stringMap 1", prefix + "string map value 1" + suffix);
        stringMap.put("stringMap 2", null);
        stringMap.put("stringMap 3", "");
        stringMap.put("stringMap 4", prefix + "string map value 4" + suffix);
        stringMap.put("stringMap 5", prefix + "string map value 5" + suffix);
        testParam.setStringMap(stringMap);

        Map<String, Boolean> booleanMap = new HashMap<>();
        booleanMap.put("booleanMap 1", false);
        booleanMap.put("booleanMap 2", null);
        booleanMap.put("booleanMap 3", true);
        booleanMap.put("booleanMap 4", false);
        booleanMap.put("booleanMap 5", false);
        testParam.setBooleanMap(booleanMap);

        Map<String, Integer> integerMap = new HashMap<>();
        integerMap.put("integerMap 1", 234);
        integerMap.put("integerMap 2", 23434);
        integerMap.put("integerMap 3", null);
        integerMap.put("integerMap 4", 9284);
        integerMap.put("integerMap 5", 234);
        testParam.setIntegerMap(integerMap);

        Map<String, Long> longMap = new HashMap<>();
        longMap.put("longMap 1", 394892L);
        longMap.put("longMap 2", null);
        longMap.put("longMap 3", 394834392L);
        longMap.put("longMap 4", 60509L);
        longMap.put("longMap 5", 98738475L);
        testParam.setLongMap(longMap);

        Map<String, Short> shortMap = new HashMap<>();
        shortMap.put("shortMap 1", (short) 343);
        shortMap.put("shortMap 2", (short) 859);
        shortMap.put("shortMap 3", null);
        shortMap.put("shortMap 4", (short) 83);
        shortMap.put("shortMap 5", (short) 7663);
        testParam.setShortMap(shortMap);

        Map<String, Float> floatMap = new HashMap<>();
        floatMap.put("floatMap 1", 3234.32F);
        floatMap.put("floatMap 2", null);
        floatMap.put("floatMap 3", 45234.22F);
        floatMap.put("floatMap 4", 9834F);
        testParam.setFloatMap(floatMap);

        Map<String, Double> doubleMap = new HashMap<>();
        doubleMap.put("doubleMap 1", 3424.223);
        doubleMap.put("doubleMap 2", 424.22);
        doubleMap.put("doubleMap 3", null);
        doubleMap.put("doubleMap 4", 24.223);
        testParam.setDoubleMap(doubleMap);

        Map<String, Character> characterMap = new HashMap<>();
        characterMap.put("characterMap 1", 'c');
        characterMap.put("characterMap 2", 'y');
        characterMap.put("characterMap 3", 'e');
        characterMap.put("characterMap 4", null);
        characterMap.put("characterMap 5", 'o');
        testParam.setCharacterMap(characterMap);

        Map<String, Byte> byteMap = new HashMap<>();
        byteMap.put("byteMap 1", (byte) 23);
        byteMap.put("byteMap 2", (byte) 232);
        byteMap.put("byteMap 3", null);
        byteMap.put("byteMap 4", (byte) 213);
        byteMap.put("byteMap 5", (byte) 123);
        testParam.setByteMap(byteMap);

        testParam.setBooleansList(
            Arrays.asList(
                new boolean[]{false, true, true, false}
                , null
                , new boolean[]{false, true, false, false}
            )
        );


//        private List<int[]> intsList;
//        private List<long[]> longsList;
//        private List<short[]> shortsList;
//        private List<float[]> floatsList;
//        private List<double[]> doublesList;
//        private List<char[]> charactersList;
//        private List<byte[]> bytesList;
//
//        /**
//         * wrapper array List
//         */
//        private List<String[]> stringsObjArrList;
//        private List<Boolean[]> booleansObjArrList;
//        private List<Integer[]> integersObjArrList;
//        private List<Long[]> longsObjArrList;
//        private List<Short[]> shortsObjArrList;
//        private List<Float[]> floatsObjArrList;
//        private List<Double[]> doublesObjArrList;
//        private List<Character[]> charactersObjArrList;
//        private List<Byte[]> bytesObjArrList;
//
//        /**
//         * wrapper Map List
//         */
//        private List<Map<String, String>> stringMapList;
//        private List<Map<String, Boolean>> booleanMapList;
//        private List<Map<String, Integer>> integerMapList;
//        private List<Map<String, Long>> longMapList;
//        private List<Map<String, Short>> shortMapList;
//        private List<Map<String, Float>> floatMapList;
//        private List<Map<String, Double>> doubleMapList;
//        private List<Map<String, Character>> characterMapList;
//        private List<Map<String, Byte>> byteMapList;
//
//        /**
//         * wrapper array Map
//         */
//        private Map<String, String[]> stringsObjArrMap;
//        private Map<String, Boolean[]> booleansObjArrMap;
//        private Map<String, Integer[]> integersObjArrMap;
//        private Map<String, Long[]> longsObjArrMap;
//        private Map<String, Short[]> shortsObjArrMap;
//        private Map<String, Float[]> floatsObjArrMap;
//        private Map<String, Double[]> doublesObjArrMap;
//        private Map<String, Character[]> charactersObjArrMap;
//        private Map<String, Byte[]> bytesObjArrMap;
//
//        /**
//         * wrapper array Map
//         */
//        private Map<String, boolean[]> booleanPrimitiveArrMap;
//        private Map<String, int[]> intPrimitiveArrMap;
//        private Map<String, long[]> longPrimitiveArrMap;
//        private Map<String, short[]> shortPrimitiveArrMap;
//        private Map<String, float[]> floatPrimitiveArrMap;
//        private Map<String, double[]> doublePrimitiveArrMap;
//        private Map<String, char[]> charPrimitiveArrMap;
//        private Map<String, byte[]> bytePrimitiveArrMap;
//
//        /**
//         * wrapper List Map
//         */
//        private Map<String, List<String>> stringListMap;
//        private Map<String, List<Boolean>> booleanListMap;
//        private Map<String, List<Integer>> integerListMap;
//        private Map<String, List<Long>> longListMap;
//        private Map<String, List<Short>> shortListMap;
//        private Map<String, List<Float>> floatListMap;
//        private Map<String, List<Double>> doubleListMap;
//        private Map<String, List<Character>> characterListMap;
//        private Map<String, List<Byte>> byteListMap;
//
//        /**
//         * object value type
//         */
//        private TestParam subObject;
//        private List<TestParam> subObjectList;
//        private Map<String, TestParam> subObjectMap;
//        private TestParam[] subObjects;
//
        return testParam;
    }

}
