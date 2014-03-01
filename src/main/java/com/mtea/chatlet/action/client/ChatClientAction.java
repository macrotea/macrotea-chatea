package com.mtea.chatlet.action.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.bristleback.server.bristle.api.annotations.ClientAction;
import pl.bristleback.server.bristle.api.annotations.ClientActionClass;

import com.mtea.chatlet.data.ChatUserDao;
import com.mtea.chatlet.model.ChatUser;

@Component
@ClientActionClass(name = "ChatClientAction")
public class ChatClientAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private ChatUserDao chatUserDao;

	public List<ChatUser> refreshUserList() {
		return chatUserDao.getAll();
	}

	@ClientAction
	public List<ChatUser> userJoined(String nickname, List<ChatUser> userList) {
		logger.debug("userJoined : [ nickname = {} , userList = {} ]", nickname, userList);
		return userList;
	}
}
