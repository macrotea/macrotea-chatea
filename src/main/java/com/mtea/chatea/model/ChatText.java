package com.mtea.chatea.model;

/**
 * 聊天信息
 * 
 * @author macrotea@qq.com
 * @since 2014年2月28日 下午4:29:13
 */
public class ChatText {

	private String nickname;

	private String msg;

	public ChatText() {
		super();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "ChatText [nickname=" + nickname + ", msg=" + msg + "]";
	}

}
