package com.epam.hackaton.bot.activities.wb;

import com.epam.hackaton.bot.skype.BotSingleton;
import com.epam.hackaton.bot.skype.BotUtils;
import com.samczsun.skype4j.Skype;

/**
 * Created by Katsiaryna_Novikava on 4/8/2017.
 */
public class HelpJobsActivity extends BaseShopActivity {

    private static final String LET_ME_HELP = "Ok, Let me Help you";

    @Override
    public void handle(String user, String msg) {

        System.out.println("Helping ...");

        Skype skype = BotSingleton.getSkypeInstance();


        BotSingleton.informUser(LET_ME_HELP);
        BotUtils.sendMessage(skype,
                "To wait for some goods, please, input\n" +
                        "Wait for <url>\n" +
                        "Check - for checking all pending tasks");       
    }
}
