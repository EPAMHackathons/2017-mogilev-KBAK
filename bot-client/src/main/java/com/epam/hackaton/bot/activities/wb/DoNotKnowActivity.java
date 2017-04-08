package com.epam.hackaton.bot.activities.wb;

import java.util.List;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.samczsun.skype4j.Skype;

public class DoNotKnowActivity implements BotActivity {

	private static final String DO_NOT_KNOW = "I am afraid I need more info to help you";
	private static final String LET_ME_HELP = "Ok Let me Help you";
	private static final String FACEPALM = "Man, You did not get enough sleep";

	@Override
	public void handle(String user, String msg) {
		System.out.println("Do Not Know ...");
		Skype skype = BotSingleton.getSkypeInstance();

		if(!botMessageWasAlreadySent(DO_NOT_KNOW)) {
			BotSingleton.informUser(DO_NOT_KNOW);
			BotUtils.sendMessage(skype, ":)");

		} else if(!botMessageWasAlreadySent(LET_ME_HELP)) {
			BotSingleton.informUser(LET_ME_HELP);
			BotUtils.sendMessage(skype, 
				"To wait for some goods, please, input\n" + 
				"Wait for <url>\n" + 
				"Check - for checking all pending tasks"); //TODO:
		} else {

			BotSingleton.informUser(FACEPALM);
			BotUtils.sendMessage(skype, "(facepalm)");
			BotSingleton.clearBotMessages();
		}
	}

	private boolean botMessageWasAlreadySent(String targetMsg) {

		List<String> messages = BotSingleton.getBotMessages();
		for(String msg : messages) {
			if(msg.toLowerCase().contains(targetMsg.toLowerCase())) {
				return true;
			}
		}

		return false;
	}
}
