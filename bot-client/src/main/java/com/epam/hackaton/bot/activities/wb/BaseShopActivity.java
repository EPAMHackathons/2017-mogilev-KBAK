package com.epam.hackaton.bot.activities.wb;

import java.util.List;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.bot.activities.BotActivityHandler;

public abstract class BaseShopActivity implements BotActivity {

	protected String getShop(String url) {
		if(url.contains("wildberries")) {
			return "WILDBERRIES";
		} else {
			return "LAMODA";
		}
	}

	protected String getUrl(List<String> messages) {
		for(String msg : messages) {
			if(msg.toLowerCase().contains(BotActivityHandler.WAIT.toLowerCase())) {
				int beginIndex = msg.indexOf("href=");
				int endIndex = msg.indexOf("\"", beginIndex + 6);
				return msg.substring(beginIndex + 6, endIndex);
			}
		}
		return "";
	}

	protected String getSize(List<String> messages) {
		for(String msg : messages) {
			if(msg.toLowerCase().contains(BotActivityHandler.SIZE.toLowerCase())) {
				return msg.substring(msg.indexOf("=") + 1).trim();
			}
		}
		return "";
	}

	protected String getPrice(List<String> messages) {
		for(String msg : messages) {
			if(msg.toLowerCase().contains(BotActivityHandler.PRICE.toLowerCase())) {
				return msg.substring(msg.indexOf("=") + 1).trim();
			}
		}
		return "";
	}
}
