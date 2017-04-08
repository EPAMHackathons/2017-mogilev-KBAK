package com.hackaton2017.processor;

import com.hackaton2017.domain.Job;
import com.hackaton2017.parser.ShopItemParser;
import com.hackaton2017.parser.impl.WildberriesShopItemParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
@Component
public class ShopItemProcessor {

    private static final Logger log = LoggerFactory.getLogger(ShopItemProcessor.class);

    @Scheduled(fixedRate = 10000)
    public void processShopItem() {
        System.out.println("SCANNING!!!!!");
        ShopItemParser parser = new WildberriesShopItemParser();
        parser.parse(new Job());

    }
}
