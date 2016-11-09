package com.ranli.fallenleavesweather.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class WeatherInformation implements Serializable{

    /**
     * aqi : {"city":{"aqi":"21","co":"0","no2":"31","o3":"30","pm10":"21","pm25":"19","qlty":"优","so2":"2"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-11-07 21:07","utc":"2016-11-07 13:07"}}
     * daily_forecast : [{"astro":{"mr":"12:40","ms":"23:15","sr":"06:50","ss":"17:05"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-11-07","hum":"52","pcpn":"0.6","pop":"98","pres":"1030","tmp":{"max":"11","min":"1"},"uv":"3","vis":"10","wind":{"deg":"338","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"mr":"13:20","ms":"null","sr":"06:51","ss":"17:04"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-11-08","hum":"44","pcpn":"0.0","pop":"4","pres":"1035","tmp":{"max":"10","min":"-1"},"uv":"3","vis":"10","wind":{"deg":"184","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"mr":"13:57","ms":"00:17","sr":"06:52","ss":"17:03"},"cond":{"code_d":"104","code_n":"502","txt_d":"阴","txt_n":"霾"},"date":"2016-11-09","hum":"58","pcpn":"0.0","pop":"4","pres":"1029","tmp":{"max":"10","min":"1"},"uv":"2","vis":"10","wind":{"deg":"197","dir":"无持续风向","sc":"微风","spd":"1"}},{"astro":{"mr":"14:33","ms":"01:22","sr":"06:53","ss":"17:02"},"cond":{"code_d":"502","code_n":"101","txt_d":"霾","txt_n":"多云"},"date":"2016-11-10","hum":"52","pcpn":"0.0","pop":"4","pres":"1017","tmp":{"max":"9","min":"1"},"uv":"2","vis":"10","wind":{"deg":"296","dir":"无持续风向","sc":"微风","spd":"4"}},{"astro":{"mr":"15:09","ms":"02:29","sr":"06:55","ss":"17:01"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-11-11","hum":"37","pcpn":"0.0","pop":"4","pres":"1017","tmp":{"max":"10","min":"3"},"uv":"2","vis":"10","wind":{"deg":"194","dir":"无持续风向","sc":"微风","spd":"8"}},{"astro":{"mr":"15:46","ms":"03:39","sr":"06:56","ss":"17:00"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-11-12","hum":"43","pcpn":"0.0","pop":"4","pres":"1019","tmp":{"max":"11","min":"0"},"uv":"-999","vis":"10","wind":{"deg":"185","dir":"无持续风向","sc":"微风","spd":"3"}},{"astro":{"mr":"16:25","ms":"04:51","sr":"06:57","ss":"17:00"},"cond":{"code_d":"101","code_n":"101","txt_d":"多云","txt_n":"多云"},"date":"2016-11-13","hum":"48","pcpn":"0.0","pop":"4","pres":"1018","tmp":{"max":"10","min":"2"},"uv":"-999","vis":"10","wind":{"deg":"154","dir":"无持续风向","sc":"微风","spd":"7"}}]
     * hourly_forecast : [{"cond":{"code":"100","txt":"晴"},"date":"2016-11-07 22:00","hum":"47","pop":"0","pres":"1034","tmp":"7","wind":{"deg":"346","dir":"西北风","sc":"微风","spd":"10"}}]
     * now : {"cond":{"code":"104","txt":"阴"},"fl":"4","hum":"32","pcpn":"0","pres":"1034","tmp":"8","vis":"10","wind":{"deg":"340","dir":"北风","sc":"4-5","spd":"24"}}
     * status : ok
     * suggestion : {"comf":{"brf":"较舒适","txt":"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。"},"cw":{"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"},"sport":{"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"},"trav":{"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"},"uv":{"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}}
     */

    public List<HeWeather5> HeWeather5;

    public static class HeWeather5 implements Serializable{
        /**
         * city : {"aqi":"21","co":"0","no2":"31","o3":"30","pm10":"21","pm25":"19","qlty":"优","so2":"2"}
         */

        public Aqi aqi;
        /**
         * city : 北京
         * cnty : 中国
         * id : CN101010100
         * lat : 39.904000
         * lon : 116.391000
         * update : {"loc":"2016-11-07 21:07","utc":"2016-11-07 13:07"}
         */

        public Basic basic;
        /**
         * cond : {"code":"104","txt":"阴"}
         * fl : 4
         * hum : 32
         * pcpn : 0
         * pres : 1034
         * tmp : 8
         * vis : 10
         * wind : {"deg":"340","dir":"北风","sc":"4-5","spd":"24"}
         */

        public Now now;
        public String status;
        /**
         * comf : {"brf":"较舒适","txt":"白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。"}
         * cw : {"brf":"较适宜","txt":"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"}
         * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
         * flu : {"brf":"较易发","txt":"昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"}
         * sport : {"brf":"较适宜","txt":"天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。"}
         * trav : {"brf":"适宜","txt":"天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。"}
         * uv : {"brf":"中等","txt":"属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。"}
         */

        public Suggestion suggestion;
        /**
         * astro : {"mr":"12:40","ms":"23:15","sr":"06:50","ss":"17:05"}
         * cond : {"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"}
         * date : 2016-11-07
         * hum : 52
         * pcpn : 0.6
         * pop : 98
         * pres : 1030
         * tmp : {"max":"11","min":"1"}
         * uv : 3
         * vis : 10
         * wind : {"deg":"338","dir":"无持续风向","sc":"微风","spd":"4"}
         */

        public List<DailyForecast> daily_forecast;
        /**
         * cond : {"code":"100","txt":"晴"}
         * date : 2016-11-07 22:00
         * hum : 47
         * pop : 0
         * pres : 1034
         * tmp : 7
         * wind : {"deg":"346","dir":"西北风","sc":"微风","spd":"10"}
         */

        public List<HourlyForecast> hourly_forecast;

        public static class Aqi implements Serializable{
            /**
             * aqi : 21
             * co : 0
             * no2 : 31
             * o3 : 30
             * pm10 : 21
             * pm25 : 19
             * qlty : 优
             * so2 : 2
             */

            public City city;

            public static class City implements Serializable{
                public String aqi;
                public String co;
                public String no2;
                public String o3;
                public String pm10;
                public String pm25;
                public String qlty;
                public String so2;
            }
        }

        public static class Basic implements Serializable{
            public String city;
            public String cnty;
            public String id;
            public String lat;
            public String lon;
            /**
             * loc : 2016-11-07 21:07
             * utc : 2016-11-07 13:07
             */

            public Update update;

            public static class Update implements Serializable{
                public String loc;
                public String utc;
            }
        }

        public static class Now implements Serializable{
            /**
             * code : 104
             * txt : 阴
             */

            public Cond cond;
            public String fl;
            public String hum;
            public String pcpn;
            public String pres;
            public String tmp;
            public String vis;
            /**
             * deg : 340
             * dir : 北风
             * sc : 4-5
             * spd : 24
             */

            public Wind wind;

            public static class Cond implements Serializable{
                public String code;
                public String txt;
            }

            public static class Wind implements Serializable{
                public String deg;
                public String dir;
                public String sc;
                public String spd;
            }
        }

        public static class Suggestion implements Serializable{
            /**
             * brf : 较舒适
             * txt : 白天虽然天气晴好，但早晚会感觉偏凉，午后舒适、宜人。
             */

            public Comf comf;
            /**
             * brf : 较适宜
             * txt : 较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。
             */

            public Cw cw;
            /**
             * brf : 较冷
             * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
             */

            public Drsg drsg;
            /**
             * brf : 较易发
             * txt : 昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。
             */

            public Flu flu;
            /**
             * brf : 较适宜
             * txt : 天气较好，但考虑气温较低，推荐您进行室内运动，若户外适当增减衣物并注意防晒。
             */

            public Sport sport;
            /**
             * brf : 适宜
             * txt : 天气较好，温度适宜，是个好天气哦。这样的天气适宜旅游，您可以尽情地享受大自然的风光。
             */

            public Trav trav;
            /**
             * brf : 中等
             * txt : 属中等强度紫外线辐射天气，外出时建议涂擦SPF高于15、PA+的防晒护肤品，戴帽子、太阳镜。
             */

            public Uv uv;

            public static class Comf implements Serializable{
                public String brf;
                public String txt;
            }

            public static class Cw implements Serializable{
                public String brf;
                public String txt;
            }

            public static class Drsg implements Serializable{
                public String brf;
                public String txt;
            }

            public static class Flu implements Serializable{
                public String brf;
                public String txt;
            }

            public static class Sport implements Serializable{
                public String brf;
                public String txt;
            }

            public static class Trav implements Serializable{
                public String brf;
                public String txt;
            }

            public static class Uv implements Serializable{
                public String brf;
                public String txt;
            }
        }

        public static class DailyForecast implements Serializable{
            /**
             * mr : 12:40
             * ms : 23:15
             * sr : 06:50
             * ss : 17:05
             */

            public Astro astro;
            /**
             * code_d : 100
             * code_n : 100
             * txt_d : 晴
             * txt_n : 晴
             */

            public Cond cond;
            public String date;
            public String hum;
            public String pcpn;
            public String pop;
            public String pres;
            /**
             * max : 11
             * min : 1
             */

            public Tmp tmp;
            public String uv;
            public String vis;
            /**
             * deg : 338
             * dir : 无持续风向
             * sc : 微风
             * spd : 4
             */

            public Wind wind;

            public static class Astro implements Serializable{
                public String mr;
                public String ms;
                public String sr;
                public String ss;
            }

            public static class Cond implements Serializable{
                public String code_d;
                public String code_n;
                public String txt_d;
                public String txt_n;
            }

            public static class Tmp implements Serializable{
                public String max;
                public String min;
            }

            public static class Wind implements Serializable{
                public String deg;
                public String dir;
                public String sc;
                public String spd;
            }
        }

        public static class HourlyForecast implements Serializable{
            /**
             * code : 100
             * txt : 晴
             */

            public Cond cond;
            public String date;
            public String hum;
            public String pop;
            public String pres;
            public String tmp;
            /**
             * deg : 346
             * dir : 西北风
             * sc : 微风
             * spd : 10
             */

            public Wind wind;

            public static class Cond implements Serializable{
                public String code;
                public String txt;
            }

            public static class Wind implements Serializable{
                public String deg;
                public String dir;
                public String sc;
                public String spd;
            }
        }
    }
}
