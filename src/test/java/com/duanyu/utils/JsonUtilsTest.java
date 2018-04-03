package com.duanyu.utils;

import org.junit.Test;

import java.util.Date;

public class JsonUtilsTest {

    @Test
    public void serializeTest() {
        User user = new User();
        user.setAge(1);
        user.setName("zs");
        user.setDate(new Date());
        user.setABc("a");
        String json = JsonUtils.serialize(user);
        System.out.println(json);
        User user2 = JsonUtils.deserialize(json, User.class);
        assert user2.getAge() == 1;
    }

}
