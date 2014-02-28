package com.mtea.chatlet.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.bristleback.server.bristle.api.action.DefaultAction;
import pl.bristleback.server.bristle.api.annotations.Action;
import pl.bristleback.server.bristle.api.annotations.ActionClass;

import com.mtea.chatlet.action.client.ChatClientAction;
import com.mtea.chatlet.data.ChatUserDao;
import com.mtea.chatlet.model.ChatUser;

@Controller
@ActionClass(name = "ChatAction")
public class ChatAction implements DefaultAction<ChatUser, String> {

	@Autowired private ChatUserDao chatUserDao;
	@Autowired private ChatClientAction chatClientAction;

	@Action
	@Override
	public Object executeDefault(ChatUser userContext, String payload) throws Exception {
		return null;
	}
	
	@Action
	public List<ChatUser> joinUser(ChatUser user, String nickname) {
		System.out.println("joinUser");
		
		user.setNickname(nickname);
		chatUserDao.add2(user);
		
		List<ChatUser> userList = chatUserDao.getAll();
		
		//通知
		chatClientAction.userJoined(nickname,userList);
		
		return userList;
	}
}
