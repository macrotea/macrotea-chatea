package com.mtea.chatlet.action.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.bristleback.server.bristle.api.annotations.ClientAction;
import pl.bristleback.server.bristle.api.annotations.ClientActionClass;

import com.mtea.chatlet.data.ChatUserDao;
import com.mtea.chatlet.model.ChatUser;

@Component
@ClientActionClass(name = "ChatClientAction")
public class ChatClientAction {

	@Autowired private ChatUserDao chatUserDao;

	public List<ChatUser> refreshUserList() {
		return chatUserDao.getAll();
	}

	@ClientAction
	public void userJoined(String nickname, List<ChatUser> userList) {
		System.out.println("userJoined");
	}
}
