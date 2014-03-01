package com.mtea.chatlet.model;

import pl.bristleback.server.bristle.engine.user.BaseUserContext;

/**
 * 聊天用户
 * 
 * @author macrotea@qq.com
 * @since 2014年2月28日 下午4:29:13
 */
public class ChatUser extends BaseUserContext {

	private String nickname;
	
	public ChatUser() {
		super();
	}

	public ChatUser(String nickname) {
		super();
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isLogged() {
		return nickname != null;
	}

	@Override
	public String toString() {
		return "ChatUser [nickname=" + nickname + ", getNickname()=" + getNickname() + ", isLogged()=" + isLogged() + ", getId()=" + getId() + "]";
	}
	
	
}
