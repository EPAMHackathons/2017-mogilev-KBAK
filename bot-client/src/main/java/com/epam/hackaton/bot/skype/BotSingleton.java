package com.epam.hackaton.bot.skype;

import java.util.ArrayList;
import java.util.List;

import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;

public class BotSingleton {

	private static Skype skype;

	private static boolean isSoundEnabled = false;

	private static String currentUserToChat;

	private static List<String> messages = new ArrayList<String>();

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

	public static void pushMsg(String msg) {
		messages.add(msg);
	}

	public static List<String> getAllMessages() {
		return messages;
	}

	public static void clearMessages() {
		messages.clear();
	}

	public static void informUser(String msg) {
		BotUtils.sendMessage(getSkypeInstance(), msg);
		SpeechUtils.speakMsg(msg);
	}
}
