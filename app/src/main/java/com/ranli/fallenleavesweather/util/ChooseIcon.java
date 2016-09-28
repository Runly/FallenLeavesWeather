package com.ranli.fallenleavesweather.util;

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
        iconMap.put("100.png", R.drawable.icon100);
        iconMap.put("101.png", R.drawable.icon101);
        iconMap.put("102.png", R.drawable.icon102);
        iconMap.put("103.png", R.drawable.icon103);
        iconMap.put("104.png", R.drawable.icon104);
        iconMap.put("200.png", R.drawable.icon200);
        iconMap.put("201.png", R.drawable.icon201);
        iconMap.put("202.png", R.drawable.icon202);
        iconMap.put("203.png", R.drawable.icon203);
        iconMap.put("204.png", R.drawable.icon204);
        iconMap.put("205.png", R.drawable.icon205);
        iconMap.put("206.png", R.drawable.icon206);
        iconMap.put("207.png", R.drawable.icon207);
        iconMap.put("208.png", R.drawable.icon208);
        iconMap.put("209.png", R.drawable.icon209);
        iconMap.put("210.png", R.drawable.icon210);
        iconMap.put("211.png", R.drawable.icon211);
        iconMap.put("212.png", R.drawable.icon212);
        iconMap.put("213.png", R.drawable.icon213);
        iconMap.put("300.png", R.drawable.icon300);
        iconMap.put("301.png", R.drawable.icon301);
        iconMap.put("302.png", R.drawable.icon302);
        iconMap.put("303.png", R.drawable.icon303);
        iconMap.put("304.png", R.drawable.icon304);
        iconMap.put("305.png", R.drawable.icon305);
        iconMap.put("306.png", R.drawable.icon306);
        iconMap.put("307.png", R.drawable.icon307);
        iconMap.put("308.png", R.drawable.icon308);
        iconMap.put("309.png", R.drawable.icon309);
        iconMap.put("310.png", R.drawable.icon310);
        iconMap.put("311.png", R.drawable.icon311);
        iconMap.put("312.png", R.drawable.icon312);
        iconMap.put("313.png", R.drawable.icon313);
        iconMap.put("400.png", R.drawable.icon400);
        iconMap.put("401.png", R.drawable.icon401);
        iconMap.put("402.png", R.drawable.icon402);
        iconMap.put("403.png", R.drawable.icon403);
        iconMap.put("404.png", R.drawable.icon404);
        iconMap.put("405.png", R.drawable.icon405);
        iconMap.put("406.png", R.drawable.icon406);
        iconMap.put("407.png", R.drawable.icon407);
        iconMap.put("500.png", R.drawable.icon500);
        iconMap.put("501.png", R.drawable.icon501);
        iconMap.put("502.png", R.drawable.icon502);
        iconMap.put("503.png", R.drawable.icon503);
        iconMap.put("504.png", R.drawable.icon504);
        iconMap.put("507.png", R.drawable.icon507);
        iconMap.put("508.png", R.drawable.icon508);
        iconMap.put("900.png", R.drawable.icon900);
        iconMap.put("901.png", R.drawable.icon901);
        iconMap.put("999.png", R.drawable.icon999);
    }

    public static synchronized ChooseIcon getInstance() {
        if (instance == null) {
            instance = new ChooseIcon();
        }
        return  instance;
    }

}
