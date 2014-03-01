package com.mtea.chatea.model;

/**
 * 聊天统计
 * @author macrotea@qq.com
 * @since 2014年3月1日 下午8:37:02
 */
public class ChatStat {
	
	private int loginTotal;

	private int msgTotal;

	public ChatStat() {
		super();
	}

	public ChatStat(int loginTotal, int msgTotal) {
		super();
		this.loginTotal = loginTotal;
		this.msgTotal = msgTotal;
	}

	public int getLoginTotal() {
		return loginTotal;
	}

	public void setLoginTotal(int loginTotal) {
		this.loginTotal = loginTotal;
	}

	public int getMsgTotal() {
		return msgTotal;
	}

	public void setMsgTotal(int msgTotal) {
		this.msgTotal = msgTotal;
	}

	@Override
	public String toString() {
		return "ChatStat [loginTotal=" + loginTotal + ", msgTotal=" + msgTotal + "]";
	}

}