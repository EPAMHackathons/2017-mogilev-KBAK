package com.epam.hackaton.bot.activities.wb;

import java.util.List;

import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.epam.hackaton.model.GoalType;
import com.epam.hackaton.model.JobType;
import com.epam.hackaton.service.JobService;
import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;

public class CreateWaitSizeJobActivity extends BaseShopActivity {

	@Override
	public void handle(String user, String msg) {
		System.out.println("Create Size Job Activity ...");
		Skype skype = BotSingleton.getSkypeInstance();

		List<String> userMessages = BotSingleton.getUserMessages();
		String size = getSize(userMessages);
		String url = getUrl(userMessages);
		String shop = getShop(url);

		JobService jobService = new JobService();
		System.out.println("Create Job: " + JobType.GETTING_ITEM_DETAILS.name() + ", " + shop + ", " + url + ", " + GoalType.SIZE_WAITING.name() + ", " + size);
		jobService.createJobWithGoals(JobType.GETTING_ITEM_DETAILS.name(), shop, url, GoalType.SIZE_WAITING.name(), size);

		BotUtils.sendMessage(skype, "You will be informed if SIZE [" + size + "] is available for [" + url + "]");
		SpeechUtils.speakMsg("We remember your choice");
		BotUtils.sendMessage(skype, "You can press the [BUTTON] to get status instantly");

		BotSingleton.clearUserMessages();
		BotSingleton.clearBotMessages();
	}
}
