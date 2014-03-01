package com.mtea.chatea.model;

/**
 * 聊天计数器
 * 
 * @author macrotea@qq.com
 * @since 2014年2月28日 下午4:29:13
 */
public class ChatCounter {

	/**
	 * 总用户登录数
	 */
	public static int loginTotalCounter = 0;

	/**
	 * 总消息发送数
	 */
	public static int msgTotalCounter = 0;

	public static void increaseLoginTotal() {
		ChatCounter.loginTotalCounter = ChatCounter.loginTotalCounter + 1;
	}

	public static void increaseMsgTotal() {
		ChatCounter.msgTotalCounter = ChatCounter.msgTotalCounter + 1;
	}

}
