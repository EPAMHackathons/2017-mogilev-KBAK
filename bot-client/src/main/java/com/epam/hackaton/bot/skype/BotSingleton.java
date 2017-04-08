package com.epam.hackaton.bot.skype;

import java.util.ArrayList;
import java.util.List;

import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;

public class BotSingleton {

	private static Skype skype;

	private static boolean isSoundEnabled = false;

	private static String currentUserToChat;

	private static List<String> userMessages = new ArrayList<String>();
	private static List<String> botMessages = new ArrayList<String>();

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

	public static void pushUserMsg(String msg) {
		userMessages.add(msg);
	}

	public static List<String> getUserMessages() {
		return userMessages;
	}

	public static void clearUserMessages() {
		userMessages.clear();
	}

	public static void pushBotMsg(String msg) {
		botMessages.add(msg);
	}

	public static List<String> getBotMessages() {
		return botMessages;
	}

	public static void clearBotMessages() {
		botMessages.clear();
	}

	public static void informUser(String msg) {
		BotUtils.sendMessage(getSkypeInstance(), msg);
		SpeechUtils.speakMsg(msg);
	}
}
