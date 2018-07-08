package com.khw.object.util;

import com.google.gson.Gson;
import com.khw.object.util.model.TestParam;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class TestObjectFilterUtils {

    private TestUtils testUtils = new TestUtils();

    @Test
    public void test_objectFilters() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", "wonder woman");
        map.put("age", 20);
        map.put("sex", "woman");

        Map<String, Object> subMap = new LinkedHashMap<>();
        subMap.put("name", "superman");
        subMap.put("age", 20);
        subMap.put("sex", "man");

        map.put("subMap", subMap);

        map = (Map<String, Object>) ObjectFilterUtils.typeObjectFilter(map, str -> str + " ---", String.class);

        System.out.println(new Gson().toJson(map));
    }

    @Test
    public void test_2() {
        List<Object> list = new ArrayList<>();

        list.add("test");

        List<String> subList = new ArrayList<>();

        subList.add("ttteee");
        list.add(subList);

        list = (List<Object>) ObjectFilterUtils.typeObjectFilter(list, str -> str + "---", String.class);

        jsonPrint(list);

        int intt = 123;

        if (Integer.class.isAssignableFrom(int.class)) {
            System.out.println(1);
        }

        Integer a = intt;

        if (int.class.isAssignableFrom(Integer.class)) {
            System.out.println(1);
        }
    }

    @Test
    public void test_3() {
        List<Object> list = new ArrayList<>();

        list.add("test");

        List<String> subList = new ArrayList<>();

        subList.add("ttteee");
        list.add(subList);

        list.add(testUtils.getTestParam("pre ", " suf"));

        list = (List<Object>) ObjectFilterUtils.typeObjectFilter(list, str -> "---", String.class);

        jsonPrint(list);
    }

    @Test
    public void test_4() {
        TestParam param = testUtils.getTestParam("---", "===");

        param = (TestParam) ObjectFilterUtils.typeObjectFilter(param, str -> "----", String.class);
        jsonPrint(param);
    }

    @Test
    public void test_5() {
        Object[] objects = new Object[5];

        List<Object> list = new ArrayList<>();

        list.add("test");

        List<String> subList = new ArrayList<>();

        subList.add("ttteee");
        list.add(subList);

        objects[0] = list;

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", "wonder woman");
        map.put("age", 20);
        map.put("sex", "woman");

        Map<String, Object> subMap = new LinkedHashMap<>();
        subMap.put("name", "superman");
        subMap.put("age", 20);
        subMap.put("sex", "man");

        map.put("subMap", subMap);

        objects[1] = map;

        List<Object> list2 = new ArrayList<>();

        list2.add("test");

        List<String> subList2 = new ArrayList<>();

        subList2.add("ttteee");
        list2.add(subList);

        list2.add(testUtils.getTestParam("pre ", " suf"));

        objects[2] = list2;

        objects = (Object[]) ObjectFilterUtils.typeObjectFilter(objects, str -> "---", String.class);
        jsonPrint(objects);
    }

    private void jsonPrint(Object obj) {
        System.out.println(new Gson().toJson(obj));
    }

}