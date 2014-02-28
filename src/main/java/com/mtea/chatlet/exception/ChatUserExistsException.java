package com.mtea.chatlet.exception;

/**
 * 聊天用户已存在异常
 * @author macrotea@qq.com
 * @since 2014年2月28日 下午4:37:13
 */
public class ChatUserExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nickname;

	public ChatUserExistsException(String nickname) {
		super();
		this.nickname = nickname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public static ChatUserExistsException newOne(String nickname){
		return new ChatUserExistsException(nickname);
	}
	
	public void throwMe(){
		throw this;
	}

}
