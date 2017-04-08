package com.epam.hackaton.bot.activities.wb;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.epam.hackaton.service.SendService;
import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;

public class CheckJobsActivity implements BotActivity {

	private static final String CHECK_URL = "http://localhost:8080/check";
	
	private static final String UNSUCCESSFUL_RESULT = "No completed jobs founded.";

	@Override
	public void handle(String user, String msg) {
		System.out.println("Check jobs Activity ...");
		Skype skype = BotSingleton.getSkypeInstance();

		SendService service = new SendService();
		String resultOfChecking =service.sendGet(CHECK_URL);

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
