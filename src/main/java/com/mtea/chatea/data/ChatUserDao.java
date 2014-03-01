package com.mtea.chatea.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.mtea.chatea.exception.ChatUserExistsException;
import com.mtea.chatea.model.ChatUser;

@Component
public class ChatUserDao {

	private static Map<String, ChatUser> userCache = new HashMap<String, ChatUser>();

	public ChatUser add(String nickname) {
		return add2(new ChatUser(nickname));
	}

	public ChatUser add2(ChatUser user) {
		String nickname = user.getNickname();
		if (userCache.get(nickname) != null) {
			ChatUserExistsException.newOne(nickname).throwMe();
		}

		userCache.put(nickname, user);
		return user;
	}

	public List<ChatUser> getAll() {
		return new ArrayList<ChatUser>(userCache.values());
	}

	public void removeAll() {
		userCache.clear();
	}

	public void remove(String nickname) {
		userCache.remove(nickname);
	}

}
