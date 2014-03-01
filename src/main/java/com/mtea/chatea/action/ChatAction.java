package com.mtea.chatea.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.bristleback.server.bristle.api.annotations.Action;
import pl.bristleback.server.bristle.api.annotations.ActionClass;

import com.mtea.chatea.action.client.ChatClientAction;
import com.mtea.chatea.data.ChatUserDao;
import com.mtea.chatea.model.ChatCounter;
import com.mtea.chatea.model.ChatUser;

@Controller
@ActionClass(name = "ChatAction")
public class ChatAction {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChatUserDao chatUserDao;

	@Autowired
	private ChatClientAction chatClientAction;

	@Action
	public ChatUser joinUser(ChatUser user, String nickname) {
		logger.debug("joinUser : [ user = {} , nickname = {} ]", user.toString(), nickname);

		user.setNickname(nickname);
		chatUserDao.add2(user);

		List<ChatUser> userList = chatUserDao.getAll();

		chatClientAction.userJoined(nickname, userList);
		
		ChatCounter.increaseLoginTotal();

		return user;
	}
	
}
