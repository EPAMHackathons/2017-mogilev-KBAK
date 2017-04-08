package com.epam.hackaton.bot.activities;

import com.epam.hackaton.bot.activities.wb.CheckJobsActivity;
import com.epam.hackaton.bot.skype.BotSingleton;

public class JobsChecker implements Runnable {

	private static final int INTERVAL = 60000;

	private String user;

	public JobsChecker(String user) {
		this.user = user;
	}

	@Override
	public void run() {

		try {
			while(true) {
				System.out.println("Check jobs for [" + user + "] ...");
				new CheckJobsActivity().handle(BotSingleton.getCurrentUserToChat(), "");
				Thread.sleep(INTERVAL);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
