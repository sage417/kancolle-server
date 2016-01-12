/**
 * 
 */
package com.kancolle.server.jackjson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kancolle.server.model.kcsapi.useitem.UseItemResult;
import com.kancolle.server.model.po.useitem.UseItem;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author J.K.SAGE
 * @Date 2015年11月6日
 *
 */
public class JackJsonTest {

    @Test
    public void test() {
        A a = new A("sage", 14, 120, 60);
        ObjectMapper om = new ObjectMapper();
        try {
            String out = om.writeValueAsString(a);
            Assert.assertEquals("{\"age\":14,\"height\":120,\"weight\":60,\"NAME\":\"sage\"}",out);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testUseItem() throws JsonProcessingException {
        UseItemResult useItem = new UseItemResult();
        ObjectMapper om = new ObjectMapper();
        System.out.print(om.writeValueAsString(useItem));



    }

    @JsonPropertyOrder(value = {  "age", "height", "weight","name" })
    private static class A {

        @JsonProperty("NAME")
        private String name;

        private int age;

        private int height;

        private int weight;

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
