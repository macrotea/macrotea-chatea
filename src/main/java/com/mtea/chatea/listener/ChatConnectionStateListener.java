package com.mtea.chatea.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.bristleback.server.bristle.api.ConnectionStateListener;
import pl.bristleback.server.bristle.listener.ConnectionStateListenerChain;

import com.mtea.chatea.action.client.ChatClientAction;
import com.mtea.chatea.data.ChatUserDao;
import com.mtea.chatea.model.ChatUser;

@Component
public class ChatConnectionStateListener implements ConnectionStateListener<ChatUser>{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChatClientAction chatClientAction;

	@Autowired
	private ChatUserDao chatUserDao;

	@Override
	public void userConnected(ChatUser chatUser, ConnectionStateListenerChain connectionStateListenerChain) {
		logger.info("用户ID = {} 进行连接",chatUser.getId());
	}

	@Override
	public void userDisconnected(ChatUser chatUser, ConnectionStateListenerChain connectionStateListenerChain) {
		if (chatUser.isLogged()) {
			logger.info("用户 = {} 失去连接",chatUser.getNickname());
			chatUserDao.remove(chatUser.getNickname());
			chatClientAction.userLeave(chatUser.getNickname(), chatUserDao.getAll());
		}
	}
}
