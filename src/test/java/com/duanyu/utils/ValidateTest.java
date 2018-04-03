package com.duanyu.utils;

import org.junit.Test;

public class ValidateTest {

    @Test
    public void phoneTest() {
        assert ValidateUtils.isPhone("13883882222");
        assert !ValidateUtils.isPhone("1388388222");
        assert !ValidateUtils.isPhone("111111111111");
        assert ValidateUtils.isPhone("16883882222");
    }

    @Test
    public void emailTest() {
        assert ValidateUtils.isEmail("aaa@aa.com");
    }

    @Test
    public void numTest() {
        assert ValidateUtils.isNum("111");
        assert ValidateUtils.isNum("111.11");
        assert !ValidateUtils.isNum("a");
        assert !ValidateUtils.isNum("1@1");
        assert !ValidateUtils.isNum("1.");
        assert !ValidateUtils.isNum(".1");
    }

    @Test
    public void idCardTest() {
        assert ValidateUtils.isIdCard("110000197206131979");
        assert !ValidateUtils.isIdCard("33323232323232323111");
    }

    @Test
    public void zhTest() {
        assert ValidateUtils.isCN("张三");
        assert ValidateUtils.isCN("张");
        assert !ValidateUtils.isCN("zs");
    }

    @Test
    public void urlTest() {
        assert ValidateUtils.isURL("http://www.51vj.cn");
        assert ValidateUtils.isURL("HTTP://www.51vj.cn");
        assert ValidateUtils.isURL("https://www.51vj.cn");
        assert ValidateUtils.isURL("https://www.51vj.cn/");
        assert ValidateUtils.isURL("HTTPS://www.51vj.cn/");
        assert !ValidateUtils.isURL("ftp://www.51vj.cn/");
    }
}
