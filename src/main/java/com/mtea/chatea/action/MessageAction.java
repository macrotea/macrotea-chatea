package com.mtea.chatea.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.bristleback.server.bristle.api.action.DefaultAction;
import pl.bristleback.server.bristle.api.annotations.Action;
import pl.bristleback.server.bristle.api.annotations.ActionClass;

import com.mtea.chatea.action.client.ChatClientAction;
import com.mtea.chatea.model.ChatText;
import com.mtea.chatea.model.ChatUser;

@Controller
@ActionClass(name = "MessageAction")
public class MessageAction implements DefaultAction<ChatUser, ChatText> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ChatClientAction chatClientAction;

	@Action
	public Void executeDefault(ChatUser user, ChatText chatText) throws Exception {
		logger.debug("executeDefault : {} ",chatText.toString());
		chatClientAction.msgSent(user,chatText);
		return null;
	}
}
