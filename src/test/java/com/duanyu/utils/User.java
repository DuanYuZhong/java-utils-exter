package com.duanyu.utils;

import java.util.Date;

public class User {

	private String name;
	private Integer age;
	private Date date;

	private String aBc;

	public String getABc() {
		return aBc;
	}

	public void setABc(String aBc) {
		this.aBc = aBc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", date=" + date +
            ", aBc='" + aBc + '\'' +
            '}';
    }
}
