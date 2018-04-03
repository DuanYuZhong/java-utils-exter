package com.duanyu.utils;

import com.google.common.eventbus.Subscribe;

public class UserHandler {

	@Subscribe
	public void test(User user) {
		user.setName("zs");
	}

}
