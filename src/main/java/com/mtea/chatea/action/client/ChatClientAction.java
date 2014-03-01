package com.mtea.chatea.action.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.bristleback.server.bristle.api.annotations.ClientAction;
import pl.bristleback.server.bristle.api.annotations.ClientActionClass;

import com.mtea.chatea.data.ChatUserDao;
import com.mtea.chatea.model.ChatStat;
import com.mtea.chatea.model.ChatText;
import com.mtea.chatea.model.ChatUser;

@Component
@ClientActionClass(name = "ChatClientAction")
public class ChatClientAction {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private ChatUserDao chatUserDao;
	
	// NOTICE macrotea@qq.com 2014/3/1 20:38:41 一定要返回List<ChatUser>

	@ClientAction
	public List<ChatUser> userJoined(String nickname, List<ChatUser> userList) {
		logger.debug("userJoined : [ nickname = {} , userList = {} ]", nickname, userList);
		return userList;
	}

	@ClientAction
	public List<ChatUser>  msgSent(ChatUser chatUser, ChatText chatText) {
		logger.debug("msgSent");
		return chatUserDao.getAll();
	}

	@ClientAction
	public List<ChatUser> userLeave(String nickname, List<ChatUser> userList) {
		return userList;
	}
	
	@ClientAction
	public List<ChatUser> statPushed(ChatStat chatStat) {
		return chatUserDao.getAll();
	}
}
