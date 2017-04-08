package com.epam.hackaton.bot.activities.wb;

import com.epam.hackaton.bot.activities.BotActivity;
import com.epam.hackaton.bot.skype.BotSingleton;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Kanstantsin_Tolstsik on 4/8/2017.
 */
public class HelloActivity implements BotActivity {
    private static final String HELLO1 = "Hello! Nice to meet you!";
    private static final String HELLO2 = "Hi! :)";
    private static final String HELLO3 = "Good evening.";
    private static final String[] HELLO = {HELLO1, HELLO2, HELLO3};

    @Override
    public void handle(String user, String msg) {

        final int messageNumber = ThreadLocalRandom.current().nextInt(0, 3);
        BotSingleton.informUser(HELLO[messageNumber]);
    }
}
