package com.generallycloud.nio.codec.http11;

import com.generallycloud.nio.AbstractLifeCycle;
import com.generallycloud.nio.common.LifeCycleUtil;
import com.generallycloud.nio.component.concurrent.EventLoopThread;

public class HttpContext extends AbstractLifeCycle {

	private static HttpContext	instance;

	private HttpSessionManager	httpSessionManager	= new HttpSessionManager();

	private EventLoopThread		taskExecutorThread;

	public static HttpContext getInstance() {
		return instance;
	}

	protected void doStart() throws Exception {
		this.taskExecutorThread = new EventLoopThread(httpSessionManager, "HTTPSession-Manager");

		this.taskExecutorThread.start();

		instance = this;
	}

	protected void doStop() throws Exception {
		LifeCycleUtil.stop(taskExecutorThread);
	}

	public HttpSessionManager getHttpSessionManager() {
		return httpSessionManager;
	}

}
