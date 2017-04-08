package com.epam.hackaton.bot.gui;

import java.util.Collection;

import com.epam.hackaton.bot.gui.listeners.ApplicationStartHandler;
import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.serialport.SerialPortListener;
import com.epam.hackaton.utilities.speech.SpeechUtils;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.user.Contact;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartWindow extends Application {

	public static final String DEFAULT_USER_TO_WRITE = "hanna.aliakseichykava"; //"live:aleks.anna.ur";
	public static final String DEFAULT_ARDUINO_PORT = "COM7";

	public static void main(String[] args) throws Exception {
		try {

			Skype skype = BotSingleton.getSkypeInstance();
			skype.setVisibility(Visibility.ONLINE);

			Collection<Contact> contacts = skype.getAllContacts();
			System.out.println("Contacts count: " + contacts.size());

			launch();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("KVAK Bot");

		BorderPane componentLayout = new BorderPane();
		componentLayout.setPadding(new Insets(20, 0, 20, 20));

		final FlowPane userInfoPane = new FlowPane();
		userInfoPane.setHgap(100);

		Label labelUser = new Label("Your skype account name (from settings):");

		TextField txtUser = new TextField();
		txtUser.setText(DEFAULT_USER_TO_WRITE);

		final FlowPane arduinoPane = new FlowPane();
		arduinoPane.setHgap(100);

		Label labelPort = new Label("Arduino port (COM):");

		TextField txtPort = new TextField();
		txtPort.setText(DEFAULT_ARDUINO_PORT);

		labelPort.setVisible(false);
		txtPort.setVisible(false);

		CheckBox checkboxArduinoEnabled = new CheckBox("Is Arduino enabled?");

		checkboxArduinoEnabled.setOnAction(new EventHandler() {

			@Override
			public void handle(Event event) {
				if (checkboxArduinoEnabled.isSelected()) {
					labelPort.setVisible(true);
					txtPort.setVisible(true);
				} else {
					labelPort.setVisible(false);
					txtPort.setVisible(false);
				}
			}
		});

		final FlowPane bottomPane = new FlowPane();
		bottomPane.setHgap(100);

		CheckBox checkboxSoundEnabled = new CheckBox("Are sounds enabled?");

		Button btnConnect = new Button("Connect");
		btnConnect.setOnAction(new ApplicationStartHandler(txtUser, checkboxArduinoEnabled, txtPort, checkboxSoundEnabled));


		componentLayout.setTop(userInfoPane);
		userInfoPane.getChildren().add(labelUser);
		userInfoPane.getChildren().add(txtUser);

		componentLayout.setCenter(arduinoPane);
		arduinoPane.getChildren().add(checkboxArduinoEnabled);
		arduinoPane.getChildren().add(labelPort);
		arduinoPane.getChildren().add(txtPort);

		componentLayout.setBottom(bottomPane);
		bottomPane.getChildren().add(checkboxSoundEnabled);
		bottomPane.getChildren().add(btnConnect);

		// Add the BorderPane to the Scene
		Scene appScene = new Scene(componentLayout, 500, 500);

		// Add the Scene to the Stage
		primaryStage.setScene(appScene);
		primaryStage.show();

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				try {
					Skype skype = BotSingleton.getSkypeInstance();
					skype.logout();

					SpeechUtils.terminateSpeaker();

					SerialPortListener.closeCurrentPort();

					stop();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
