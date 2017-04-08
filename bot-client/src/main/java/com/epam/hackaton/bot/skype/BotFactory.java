package com.epam.hackaton.bot.skype;

import java.io.File;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.json.JSONObject;

import com.epam.hackaton.bot.activities.BotActivityHandler;
import com.epam.hackaton.bot.skype.helper.LiveLoginHelper;
import com.epam.hackaton.bot.skype.helper.MSFTSkypeClient;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.events.EventHandler;
import com.samczsun.skype4j.events.Listener;
import com.samczsun.skype4j.events.chat.message.MessageEvent;
import com.samczsun.skype4j.events.chat.sent.PictureReceivedEvent;
import com.samczsun.skype4j.events.chat.user.action.OptionUpdateEvent;
import com.samczsun.skype4j.events.chat.user.action.PictureUpdateEvent;
import com.samczsun.skype4j.events.chat.user.action.RoleUpdateEvent;
import com.samczsun.skype4j.events.chat.user.action.TopicUpdateEvent;
import com.samczsun.skype4j.events.contact.ContactRequestEvent;
import com.samczsun.skype4j.exceptions.ConnectionException;

public class BotFactory {

	//private static final String BOT_NAME = "aleks.anna.ur@gmail.com";
	//private static final String BOT_PASS = "friend_p@ss";
	
	private static final String BOT_DISPLAY_NAME = "KVAK";
	private static final String BOT_NAME = "kvak.bot@gmail.com";
	private static final String BOT_PASS = "kv@k-2017";

	public static Skype getOldSkypeAccount(String login, String password) {
		Skype skype = new SkypeBuilder(login, password).withAllResources().build();
		loginAndAttachListeners(skype);
		return skype;
	}

	public static Skype getGuestSkypeAccount(String login, String password) {
		Skype skype = new SkypeBuilder("Skype4JGuest").withChat("19:42abed183a95456ea1de9e2f7356163c@thread.skype").withAllResources().build();
		loginAndAttachListeners(skype);
		return skype;
	}

	public static Skype getBot() {
		Skype skype = getMicrosoftSkypeAccount(BOT_NAME, BOT_PASS);
		loginAndAttachListeners(skype);
		return skype;
	}

	/**
	 * For newly created Skype accounts (that are connected to Microsoft account)
	 * @throws Exception 
	 */
	public static Skype getMicrosoftSkypeAccount(String login, String password) {

		try {
			JSONObject object = LiveLoginHelper.getXTokenObject(login, password);
			String skypeToken = object.getString("skypetoken");
			String skypeId = object.getString("skypeid");
	
			Skype skype = new MSFTSkypeClient.Builder(skypeToken, skypeId).withLogger(Logger.getGlobal())
				// .withExceptionHandler(handler)
				.withAllResources().build();
	
			return skype;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Bot instantiation Exception: " + e.getMessage());
		}
	}

	private static void loginAndAttachListeners(Skype skype) {
		try {

			skype.logout();

			skype.login();
			System.out.println("Logged in");

			skype.getEventDispatcher().registerListener(new Listener() {

				//!!!!!!!!!!!!!
				@EventHandler
				public void onMessage(MessageEvent e) throws ConnectionException {
					String msg = e.getMessage().getContent().toString();

					if(e.getMessage().getSender().getDisplayName().equals(BOT_DISPLAY_NAME)) {
						System.out.println("BOT Message: [" + msg + "] sent by " + e.getMessage().getSender().getDisplayName());
						BotSingleton.pushBotMsg(msg);
					} else {
						System.out.println("Incoming Message: [" + msg + "] sent by " + 
								e.getMessage().getSender().getDisplayName() + " [" + e.getMessage().getSender().getUsername() + "]");

						BotSingleton.pushUserMsg(msg);
						BotActivityHandler.onIncomingMessage(e.getMessage().getSender().getUsername(), msg);
					}
				}

				@EventHandler
				public void onMessage(PictureReceivedEvent e) {
					try {
						System.out.println(
								"Picture: " + e.getOriginalName() + " sent by " + e.getSender().getDisplayName());
						System.out.println("Saving to " + new File(e.getOriginalName()).getCanonicalPath());
						ImageIO.write(e.getSentImage(), "png", new File(e.getOriginalName()));
					} catch (Exception e1) {
					}
				}

				@EventHandler
				public void onPicture(PictureUpdateEvent event) {
					System.out.println(
							"Picture for " + event.getChat().getIdentity() + " was set to " + event.getPictureURL()
									+ " at " + event.getEventTime() + " by " + event.getUser().getUsername());
				}

				@EventHandler
				public void onTopic(TopicUpdateEvent event) {
					System.out
							.println("Topic for " + event.getChat().getIdentity() + " was set to " + event.getNewTopic()
									+ " at " + event.getEventTime() + " by " + event.getUser().getUsername());
				}

				@EventHandler
				public void onOption(OptionUpdateEvent event) {
					System.out.println(
							event.getOption() + " was set to " + event.isEnabled() + " at " + event.getEventTime());
				}

				@EventHandler
				public void onRole(RoleUpdateEvent event) {
					System.out
							.println("Role for " + event.getTarget().getUsername() + " was set to " + event.getNewRole()
									+ " at " + event.getEventTime() + " by " + event.getUser().getUsername());
				}

				@EventHandler
				public void onContact(ContactRequestEvent event) throws ConnectionException {
					System.out.println("New contact request from " + event.getRequest().getSender().getUsername()
							+ " at " + event.getRequest().getTime() + " with message "
							+ event.getRequest().getMessage());
				}

			});

			skype.subscribe();
			System.out.println("Subscribed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Bot instantiation Exception: " + e.getMessage());
		}

	}
}
