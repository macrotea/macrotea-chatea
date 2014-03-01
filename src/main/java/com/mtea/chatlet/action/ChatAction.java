package com.mtea.chatlet.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.bristleback.server.bristle.api.annotations.Action;
import pl.bristleback.server.bristle.api.annotations.ActionClass;

import com.mtea.chatlet.action.client.ChatClientAction;
import com.mtea.chatlet.data.ChatUserDao;
import com.mtea.chatlet.model.ChatUser;

@Controller
@ActionClass(name = "ChatAction")
public class ChatAction{// implements DefaultAction<ChatUser, String> 

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChatUserDao chatUserDao;

	@Autowired
	private ChatClientAction chatClientAction;

//	@Action
//	@Override
//	public Object executeDefault(ChatUser userContext, String payload) throws Exception {
//		logger.debug("executeDefault : [ userContext = {} , payload = {} ]", userContext, payload);
//
//		return null;
//	}

	@Action
	public List<ChatUser> joinUser(ChatUser user, String nickname) {
		logger.debug("joinUser : [ user = {} , nickname = {} ]", user.toString(), nickname);

		user.setNickname(nickname);
		// TODO macrotea@qq.com 2014/2/28 20:11:38 
		chatUserDao.removeAll();
		chatUserDao.add2(user);

		List<ChatUser> userList = chatUserDao.getAll();

		// 通知
		chatClientAction.userJoined(nickname, userList);

		return userList;
	}
}
