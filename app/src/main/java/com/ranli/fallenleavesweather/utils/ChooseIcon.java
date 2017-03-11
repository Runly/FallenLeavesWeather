package com.ranli.fallenleavesweather.utils;

import com.ranli.fallenleavesweather.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/24.
 */
public class ChooseIcon {
    public static ChooseIcon instance;

    private Map<String, Integer> iconMap;

    public Map<String, Integer> getIconMap() {
        return iconMap;
    }

    private ChooseIcon() {
        iconMap = new HashMap<>();
        iconMap.put("100.png", R.mipmap.icon100);
        iconMap.put("101.png", R.mipmap.icon101);
        iconMap.put("102.png", R.mipmap.icon102);
        iconMap.put("103.png", R.mipmap.icon103);
        iconMap.put("104.png", R.mipmap.icon104);
        iconMap.put("200.png", R.mipmap.icon200);
        iconMap.put("201.png", R.mipmap.icon201);
        iconMap.put("202.png", R.mipmap.icon202);
        iconMap.put("203.png", R.mipmap.icon203);
        iconMap.put("204.png", R.mipmap.icon204);
        iconMap.put("205.png", R.mipmap.icon205);
        iconMap.put("206.png", R.mipmap.icon206);
        iconMap.put("207.png", R.mipmap.icon207);
        iconMap.put("208.png", R.mipmap.icon208);
        iconMap.put("209.png", R.mipmap.icon209);
        iconMap.put("210.png", R.mipmap.icon210);
        iconMap.put("211.png", R.mipmap.icon211);
        iconMap.put("212.png", R.mipmap.icon212);
        iconMap.put("213.png", R.mipmap.icon213);
        iconMap.put("300.png", R.mipmap.icon300);
        iconMap.put("301.png", R.mipmap.icon301);
        iconMap.put("302.png", R.mipmap.icon302);
        iconMap.put("303.png", R.mipmap.icon303);
        iconMap.put("304.png", R.mipmap.icon304);
        iconMap.put("305.png", R.mipmap.icon305);
        iconMap.put("306.png", R.mipmap.icon306);
        iconMap.put("307.png", R.mipmap.icon307);
        iconMap.put("308.png", R.mipmap.icon308);
        iconMap.put("309.png", R.mipmap.icon309);
        iconMap.put("310.png", R.mipmap.icon310);
        iconMap.put("311.png", R.mipmap.icon311);
        iconMap.put("312.png", R.mipmap.icon312);
        iconMap.put("313.png", R.mipmap.icon313);
        iconMap.put("400.png", R.mipmap.icon400);
        iconMap.put("401.png", R.mipmap.icon401);
        iconMap.put("402.png", R.mipmap.icon402);
        iconMap.put("403.png", R.mipmap.icon403);
        iconMap.put("404.png", R.mipmap.icon404);
        iconMap.put("405.png", R.mipmap.icon405);
        iconMap.put("406.png", R.mipmap.icon406);
        iconMap.put("407.png", R.mipmap.icon407);
        iconMap.put("500.png", R.mipmap.icon500);
        iconMap.put("501.png", R.mipmap.icon501);
        iconMap.put("502.png", R.mipmap.icon502);
        iconMap.put("503.png", R.mipmap.icon503);
        iconMap.put("504.png", R.mipmap.icon504);
        iconMap.put("507.png", R.mipmap.icon507);
        iconMap.put("508.png", R.mipmap.icon508);
        iconMap.put("900.png", R.mipmap.icon900);
        iconMap.put("901.png", R.mipmap.icon901);
        iconMap.put("999.png", R.mipmap.icon999);
    }

    public static synchronized ChooseIcon getInstance() {
        if (instance == null) {
            instance = new ChooseIcon();
        }
        return  instance;
    }

}
