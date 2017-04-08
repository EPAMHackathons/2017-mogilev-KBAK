package com.epam.hackaton.bot.skype;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.chat.GroupChat;
import com.samczsun.skype4j.events.chat.user.action.OptionUpdateEvent;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.NoPermissionException;
import com.samczsun.skype4j.formatting.Message;
import com.samczsun.skype4j.formatting.Text;
import com.samczsun.skype4j.user.User;

public class BotUtils {

	/**
	 * Not for Guest
	 */
	public static GroupChat createGroupChat(Skype skype, String friend, String chatTopic) throws ConnectionException, NoPermissionException {
		// Not For guest
		GroupChat groupChat = (GroupChat) skype.createGroupChat(skype.getOrLoadContact(friend));

		groupChat.sendMessage("Chat is created");
		groupChat.sendMessage(Message.create().with(Text.rich("Created with Skype4J").withBold()));
		groupChat.setTopic(chatTopic);
		groupChat.setOptionEnabled(OptionUpdateEvent.Option.HISTORY_DISCLOSED, true);
		groupChat.setOptionEnabled(OptionUpdateEvent.Option.JOINING_ENABLED, true);
		groupChat.getUser(friend).setRole(User.Role.ADMIN);
		//groupChat.kick(friend);
		System.out.println("Join url: " + groupChat.getJoinUrl());

		return groupChat;
	}

	/**
	 * Not for Guest
	 */
	public static synchronized Chat createPrivateChat(Skype skype, String friend) throws ConnectionException, ChatNotFoundException {
		return skype.getOrLoadContact(friend).getPrivateConversation();
	}

	/**
	 * Creates chart or sends contact request
	 */
	public static Chat sendContactRequest(Skype skype, String friend) throws ConnectionException, ChatNotFoundException {
		Chat chat = createPrivateChat(skype, friend);
		chat.sendMessage("Ping");
		return chat;
	}

	public static Chat sendMessage(Skype skype, String msg) {
		try {
			String friend = BotSingleton.getCurrentUserToChat();
			Chat chat = createPrivateChat(skype, friend);
			chat.sendMessage(msg);
			return chat;

		} catch (ConnectionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ChatNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
