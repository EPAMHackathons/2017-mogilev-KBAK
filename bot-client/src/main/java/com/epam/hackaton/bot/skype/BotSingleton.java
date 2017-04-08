package com.epam.hackaton.bot.skype;

import com.samczsun.skype4j.Skype;

public class BotSingleton {

	private static Skype skype;

	private static boolean isSoundEnabled = false;

	private static String currentUserToChat;

	public static Skype getSkypeInstance() {
		if(skype == null) {
			skype = BotFactory.getBot();
		}
		return skype;
	}

	public static boolean isSoundEnabled() {
		return isSoundEnabled;
	}

	public static void setSoundEnabled(boolean isSoundEnabled) {
		BotSingleton.isSoundEnabled = isSoundEnabled;
	}

	public static String getCurrentUserToChat() {
		return currentUserToChat;
	}

	public static void setCurrentUserToChat(String currentUserToChat) {
		BotSingleton.currentUserToChat = currentUserToChat;
	}
}
