package com.mtea.chatlet.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mtea.chatlet.exception.ChatUserExistsException;
import com.mtea.chatlet.model.ChatUser;

@Component
public class ChatUserDao {

	private static Map<String, ChatUser> userCache = new HashMap<String, ChatUser>();

	public ChatUser add(String nickname) {
		return add2(new ChatUser(nickname));
	}
	
	public ChatUser add2(ChatUser user) {
		String nickname = user.getNickname();
		if(userCache.get(nickname)!=null){
			ChatUserExistsException.newOne(nickname).throwMe();
		}
		
		ChatUser u = new ChatUser(nickname);
		userCache.put(nickname, u);
		return u;
	}
	
	public List<ChatUser> getAll() {
		return new ArrayList<ChatUser>(userCache.values());
	}

}
