package com.epam.hackaton.bot.activities.wb;

import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;

public class CheckJobsActivity extends BaseCheckJobsActivity {

	private static final String UNSUCCESSFUL_RESULT = "No completed jobs founded.";

	@Override
	public void handle(String user, String msg) {
		System.out.println("Check jobs Activity ...");
		Skype skype = BotSingleton.getSkypeInstance();

		String resultOfChecking = getJobsResult();

		BotSingleton.informUser("Result");
		if(UNSUCCESSFUL_RESULT.contains(resultOfChecking)) {
			BotSingleton.informUser("Nothing is found");
			BotUtils.sendMessage(skype, "(red)"); 
		} else {
			SpeechUtils.speakMsg("Found");
			BotUtils.sendMessage(skype, "(monkey)");
			BotUtils.sendMessage(skype, resultOfChecking); 
		}
	}
}
