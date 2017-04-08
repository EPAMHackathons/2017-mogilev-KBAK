package com.epam.hackaton.bot.activities;

public class JobsChecker implements Runnable {

	private static final int INTERVAL = 5000;

	private String user;

	public JobsChecker(String user) {
		this.user = user;
	}

	@Override
	public void run() {

		try {
			while(true) {
				System.out.println("Check jobs for [" + user + "] ...");
				Thread.sleep(INTERVAL);
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
