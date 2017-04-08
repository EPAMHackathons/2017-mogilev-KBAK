package com.epam.hackaton.bot.activities;

import com.epam.hackaton.bot.activities.wb.CheckJobsBackgroundActivity;
import com.epam.hackaton.bot.skype.BotSingleton;

public class JobsChecker implements Runnable {

	private static final int INTERVAL = 20000;

	private String user;

	public JobsChecker(String user) {
		this.user = user;
	}

	@Override
	public void run() {

	try {
			Thread.sleep(10000);

			while(true) {
				System.out.println("Check jobs for [" + user + "] ...");
				new CheckJobsBackgroundActivity().handle(BotSingleton.getCurrentUserToChat(), "");
				Thread.sleep(INTERVAL);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
