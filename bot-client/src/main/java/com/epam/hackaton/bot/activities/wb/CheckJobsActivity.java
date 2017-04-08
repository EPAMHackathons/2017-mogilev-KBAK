package com.epam.hackaton.bot.activities.wb;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;

public class CheckJobsActivity implements BotActivity {

	@Override
	public void handle(String user, String msg) {
		System.out.println("Check jobs Activity ...");
		Skype skype = BotSingleton.getSkypeInstance();
		//TODO:

		BotSingleton.informUser("Result");
		BotUtils.sendMessage(skype, "(chicksegg)"); //+
		BotUtils.sendMessage(skype, "(red)"); //-
		//TODO:
		//BotUtils.sendMessage(skype, "");

		//BotSingleton.clearUserMessages();
		//BotSingleton.clearBotMessages();
	}
}
