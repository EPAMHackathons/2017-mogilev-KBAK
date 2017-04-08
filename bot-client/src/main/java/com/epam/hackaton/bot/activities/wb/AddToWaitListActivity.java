package com.epam.hackaton.bot.activities.wb;

import java.util.List;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;

public class AddToWaitListActivity extends BaseShopActivity {

	@Override
	public void handle(String user, String msg) {
		System.out.println("Wait Activity ...");
		Skype skype = BotSingleton.getSkypeInstance();
		
		List<String> userMessages = BotSingleton.getUserMessages();

		String url = getUrl(userMessages);
		
		if (!url.isEmpty()) {
	

		BotUtils.sendMessage(skype, "Please, enter size in the format <SIZE=...> or expected price in the format <PRICE=>");
		SpeechUtils.speakMsg("Please, enter size or price to wait");
		} else {

			deleteIncorrectUrl(userMessages);

			BotUtils.sendMessage(skype, "Please, enter Wait for <url>");

		}
	}

}
