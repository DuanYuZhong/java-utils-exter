package com.duanyu.utils;

import org.junit.Test;

public class EventBusTest {

	@Test
	public void deadlineTest() {
		EventHandler.post("boom");
	}

	@Test
	public void routeTest() {
		EventHandler.register("user", new UserHandler());
		User user = new User();
		EventHandler.post("user", user);
		assert "zs".equals(user.getName());

	}

	@Test
    public void asyncRouteTest() throws InterruptedException {
	    EventHandler.registerAsync("user", new UserHandler());
	    User user = new User();
	    EventHandler.postAsync("user", user);
	    assert !"zs".equals(user.getName());
	    Thread.sleep(1000);
        assert "zs".equals(user.getName());
    }

}
