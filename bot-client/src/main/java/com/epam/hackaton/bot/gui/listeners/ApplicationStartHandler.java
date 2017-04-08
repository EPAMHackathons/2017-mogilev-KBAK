package com.epam.hackaton.bot.gui.listeners;

import com.epam.hackaton.bot.activities.JobsChecker;
import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.epam.hackaton.serialport.SerialPortListener;
import com.samczsun.skype4j.Skype;

import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;

public class ApplicationStartHandler implements EventHandler {

	TextField txtUser;
	CheckBox checkboxArduinoEnabled;
	TextField txtPort;
	CheckBox checkboxSoundEnabled;

	public ApplicationStartHandler(TextField txtUser, CheckBox checkboxArduinoEnabled, TextField txtPort,
			CheckBox checkboxSoundEnabled) {
		super();
		this.txtUser = txtUser;
		this.checkboxArduinoEnabled = checkboxArduinoEnabled;
		this.txtPort = txtPort;
		this.checkboxSoundEnabled = checkboxSoundEnabled;
	}

	@Override
	public void handle(Event event) {
		String user = txtUser.getText();
		System.out.println("Invitation request is sent to the [" + user + "]");

		try {

			Skype skype = BotSingleton.getSkypeInstance();
			BotSingleton.setSoundEnabled(checkboxSoundEnabled.isSelected());
			BotSingleton.setCurrentUserToChat(user);

			BotUtils.sendContactRequest(skype, user);

			boolean isArduinoEnabled = checkboxArduinoEnabled.isSelected();
			if(isArduinoEnabled) {
				SerialPortListener portListener = new SerialPortListener();
				portListener.initialize(txtPort.getText());
			}

			Thread jobsChecker = new Thread(new JobsChecker(user));
			jobsChecker.setDaemon(true);
			jobsChecker.start();

			Object source = event.getSource();
			if (source instanceof Button) {
				((Button)source).setDisable(true);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}
}
