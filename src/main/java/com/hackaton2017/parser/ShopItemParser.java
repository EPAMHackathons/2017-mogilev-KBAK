package com.hackaton2017.parser;

import com.hackaton2017.domain.Job;
import com.hackaton2017.domain.ShopItem;

/**
 * Created by Kanstantsin_Tolstsik on 4/4/2017.
 */
public interface ShopItemParser {

    ShopItem parse(final Job job);
}
