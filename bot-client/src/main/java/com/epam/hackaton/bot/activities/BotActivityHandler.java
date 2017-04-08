package com.epam.hackaton.bot.activities;

import java.util.HashMap;
import java.util.Map;

import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.samczsun.skype4j.Skype;

public class BotActivityHandler {

	private static final Map<String, BotActivity> AVAILABLE_ACTIVITIES = getAvailableActivities();

	public static void defaultBotActivity() {
		System.out.println("Default Bot Activity");

		Skype skype = BotSingleton.getSkypeInstance();

		BotUtils.sendMessage(skype, ":)");
	}

	public static void emptyBotActivity() {
		System.out.println("Empty Bot Activity");

		Skype skype = BotSingleton.getSkypeInstance();

		BotUtils.sendMessage(skype, ":)");
	}

	public static void onIncomingMessage(String user, String msg) {
		System.out.println("On Message: From (" + user + ") - (" + msg + ")");

		BotActivity activity = findAppropriateActivity(msg);
		if(activity != null) {
			activity.handle(user, msg);
		} else {
			emptyBotActivity();
		}
	}

	private static Map<String, BotActivity> getAvailableActivities() {
		Map<String, BotActivity> activities = new HashMap<String, BotActivity>();
		//activities.put("WB", new WBActivity());
		return activities;
	}

	private static BotActivity findAppropriateActivity(String msg) {
		for(String key : AVAILABLE_ACTIVITIES.keySet()) {
			if(msg.toLowerCase().contains(key.toLowerCase())) {
				return AVAILABLE_ACTIVITIES.get(key);
			}
		}
		return null;
	}
}
