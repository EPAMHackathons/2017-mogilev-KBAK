package com.epam.hackaton.bot.activities.wb;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.service.SendService;

public abstract class BaseCheckJobsActivity implements BotActivity {

	private static final String CHECK_URL = "http://localhost:8080/check";

	protected String getJobsResult() {
		SendService service = new SendService();
		String resultOfChecking =service.sendGet(CHECK_URL);
		return resultOfChecking;
	}
}
