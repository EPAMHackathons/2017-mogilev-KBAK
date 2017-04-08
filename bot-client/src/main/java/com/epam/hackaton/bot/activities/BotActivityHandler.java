package com.epam.hackaton.bot.activities;

import java.util.HashMap;
import java.util.Map;

import com.epam.hackaton.bot.activities.wb.*;
import com.epam.hackaton.bot.skype.BotSingleton;

public class BotActivityHandler {

	private static final Map<String, BotActivity> AVAILABLE_ACTIVITIES = getAvailableActivities();

	public static final String WAIT = "WAIT";
	public static final String SIZE = "SIZE";
	public static final String PRICE = "PRICE";
	public static final String CHECK = "CHECK";
	public static final String HELLO = "HELLO";
	public static final String HI = "HI";
	public static final String HELP = "HELP";

	public static void defaultBotActivity() {
		System.out.println("Default Bot Activity");

		BotSingleton.informUser("Don't press so hard!");

		new CheckJobsActivity().handle(BotSingleton.getCurrentUserToChat(), "");
	}

	public static void onIncomingMessage(String user, String msg) {
		System.out.println("On Message: From (" + user + ") - (" + msg + ")");

		BotActivity activity = findAppropriateActivity(msg);
		activity.handle(user, msg);
	}

	private static Map<String, BotActivity> getAvailableActivities() {
		Map<String, BotActivity> activities = new HashMap<String, BotActivity>();
		activities.put(WAIT, new AddToWaitListActivity());
		activities.put(SIZE, new CreateWaitSizeJobActivity());
		activities.put(PRICE, new CreateWaitPriceJobActivity());
		activities.put(CHECK, new CheckJobsActivity());
		activities.put(HELLO, new HelloActivity());
		activities.put(HI, new HelloActivity());
		activities.put(HELP, new HelpJobsActivity());
		return activities;
	}

	private static BotActivity findAppropriateActivity(String msg) {
		for(String key : AVAILABLE_ACTIVITIES.keySet()) {
			if(msg.toLowerCase().contains(key.toLowerCase())) {
				return AVAILABLE_ACTIVITIES.get(key);
			}
		}
		return new DoNotKnowActivity();
	}
}
