/**
 *
 */
package com.kancolle.server.jackjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kancolle.server.model.kcsapi.useitem.UseItemResult;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年11月6日
 */
public class JackJsonTest {

    @Test
    public void testReadArrayNode() throws IOException {
        String arrStr = "[1,2,3]";
        ObjectMapper mapper = new ObjectMapper();
        List<Long> longList = mapper.readValue(arrStr, mapper.getTypeFactory().constructCollectionType(List.class, Long.class));
        long[] result = mapper.convertValue(longList, long[].class);
        Assert.assertArrayEquals(new long[]{1L, 2L, 3L}, result);
    }

    @Test
    public void testUnicode() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        A a = new A("中文", 14, 120, 60);
        String out = objectMapper.writeValueAsString(a);
        Assert.assertEquals("{\"friends\":\"\",\"age\":14,\"height\":120,\"weight\":60,\"NAME\":\"\\u4E2D\\u6587\"}", out);
    }

    @Test
    public void test() throws JsonProcessingException {
        A a = new A("sage", 14, 120, 60);
        ObjectMapper om = new ObjectMapper();
        String out = om.writeValueAsString(a);
        Assert.assertEquals("{\"friends\":\"\",\"age\":14,\"height\":120,\"weight\":60,\"NAME\":\"sage\"}", out);
    }

    @Test
    public void testUseItem() throws JsonProcessingException {
        UseItemResult useItem = new UseItemResult();
        ObjectMapper om = new ObjectMapper();
        System.out.print(om.writeValueAsString(useItem));
    }

    @Test
    public void testIndex() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        B b = new B();
        b.setAge(1);
        b.setHeight(2);
        b.setName("name");
        String out = mapper.writeValueAsString(b);

        System.out.println(out);


    }

    @Test
    public void testOrder() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        S s = new S();
        s.setId(2);
        s.setValue(3);
        s.setLen(4);
        Assert.assertEquals("{\"id\":2,\"value\":3,\"len\":4}", mapper.writeValueAsString(s));
    }


    private static class P {
        private int id;

        private int len;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLen() {
            return len;
        }

        public void setLen(int len) {
            this.len = len;
        }
    }

    @JsonPropertyOrder(value = {"id", "value", "len"})
    private static class S extends P {

        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }


    private static class B {

        @JsonProperty(index = 3)
        private String name;

        @JsonProperty(index = 2)
        private int age;

        @JsonProperty(index = 1)
        private int height;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    @JsonPropertyOrder(value = {"friends", "age", "height", "weight", "name"})
    private static class A {

        @JsonProperty("NAME")
        private String name;

        private int age;

        private int height;

        private int weight;

        @JsonProperty("friends")
        public String getFriendNames() {
            return "";
        }

        public A(String name, int age, int height, int weight) {
            super();
            this.name = name;
            this.age = age;
            this.height = height;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

}
