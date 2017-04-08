package com.epam.hackaton.bot.activities.wb;

import java.util.Iterator;
import java.util.List;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.bot.activities.BotActivityHandler;

public abstract class BaseShopActivity implements BotActivity {

    protected String getShop(String url) {
        if (url.contains("wildberries")) {
            return "WILDBERRIES";
        } else {
            return "LAMODA";
        }
    }

    protected String getUrl(List<String> messages) {

        String result = "";

        for (String msg : messages) {
            if (msg.toLowerCase().contains(BotActivityHandler.WAIT.toLowerCase())) {
                int beginIndex = msg.indexOf("href=");
                if(beginIndex > 0) {
                    int endIndex = msg.indexOf("\"", beginIndex + 6);
                    result = msg.substring(beginIndex + 6, endIndex);
                } else {
                    System.out.println("Href is not found: " + msg);
                }
            }
        }
        return result;
    }

    protected String getSize(List<String> messages) {

        String result = "";

        for (String msg : messages) {
            if (msg.toLowerCase().contains(BotActivityHandler.SIZE.toLowerCase())) {
                result = msg.substring(msg.indexOf("=") + 1).trim();
            }
        }
        return result;
    }

    protected String getPrice(List<String> messages) {

        String result = "";

        for (String msg : messages) {
            if (msg.toLowerCase().contains(BotActivityHandler.PRICE.toLowerCase())) {
                result = msg.substring(msg.indexOf("=") + 1).trim();
            }
        }
        return result;
    }

    protected void deleteIncorrectInput(List<String> messages) {

        for (Iterator<String> iter = messages.listIterator(); iter.hasNext(); ) {

            String msg = iter.next();

            if (msg.toLowerCase().contains(BotActivityHandler.PRICE.toLowerCase())) {
                iter.remove();
            }

            if (msg.toLowerCase().contains(BotActivityHandler.SIZE.toLowerCase())) {
                iter.remove();
            }
        }
    }

    protected void deleteIncorrectUrl(List<String> messages) {

        for (Iterator<String> iter = messages.listIterator(); iter.hasNext(); ) {

            String msg = iter.next();

            if (msg.toLowerCase().contains(BotActivityHandler.WAIT.toLowerCase())) {
                iter.remove();
            }

        }
    }
}
