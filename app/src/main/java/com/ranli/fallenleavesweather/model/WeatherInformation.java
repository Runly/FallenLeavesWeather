package com.ranli.fallenleavesweather.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4 0004.
 */

public class WeatherInformation implements Serializable{

    private static final long serialVersionUID = 8088539235914663819L;
    /**
     * city : {"aqi":"250","co":"3","no2":"104","o3":"4","pm10":"160","pm25":"250","qlty":"重度污染","so2":"4"}
     */

    @SerializedName("HeWeather data service 3.0")
    public List<WeatherInformation> list;

    private Aqi aqi;
    /**
     * city : 北京
     * cnty : 中国
     * id : CN101010100
     * lat : 39.904000
     * lon : 116.391000
     * update : {"loc":"2016-11-04 09:53","utc":"2016-11-04 01:53"}
     */

    private Basic basic;
    /**
     * cond : {"code":"501","txt":"雾"}
     * fl : 2
     * hum : 92
     * pcpn : 0
     * pres : 1011
     * tmp : 5
     * vis : 1
     * wind : {"deg":"60","dir":"东南风","sc":"3-4","spd":"16"}
     */

    private Now now;
    /**
     * aqi : {"city":{"aqi":"250","co":"3","no2":"104","o3":"4","pm10":"160","pm25":"250","qlty":"重度污染","so2":"4"}}
     * basic : {"city":"北京","cnty":"中国","id":"CN101010100","lat":"39.904000","lon":"116.391000","update":{"loc":"2016-11-04 09:53","utc":"2016-11-04 01:53"}}
     * daily_forecast : [{"astro":{"sr":"06:47","ss":"17:08"},"cond":{"code_d":"502","code_n":"502","txt_d":"霾","txt_n":"霾"},"date":"2016-11-04","hum":"45","pcpn":"0.0","pop":"0","pres":"1011","tmp":{"max":"15","min":"4"},"vis":"10","wind":{"deg":"168","dir":"无持续风向","sc":"微风","spd":"6"}},{"astro":{"sr":"06:48","ss":"17:07"},"cond":{"code_d":"101","code_n":"104","txt_d":"多云","txt_n":"阴"},"date":"2016-11-05","hum":"45","pcpn":"0.0","pop":"0","pres":"1018","tmp":{"max":"17","min":"3"},"vis":"10","wind":{"deg":"126","dir":"无持续风向","sc":"微风","spd":"7"}},{"astro":{"sr":"06:49","ss":"17:06"},"cond":{"code_d":"104","code_n":"305","txt_d":"阴","txt_n":"小雨"},"date":"2016-11-06","hum":"57","pcpn":"2.5","pop":"87","pres":"1027","tmp":{"max":"10","min":"4"},"vis":"10","wind":{"deg":"179","dir":"无持续风向","sc":"微风","spd":"9"}},{"astro":{"sr":"06:50","ss":"17:05"},"cond":{"code_d":"104","code_n":"100","txt_d":"阴","txt_n":"晴"},"date":"2016-11-07","hum":"52","pcpn":"0.0","pop":"50","pres":"1031","tmp":{"max":"12","min":"2"},"vis":"10","wind":{"deg":"351","dir":"北风","sc":"4-5","spd":"24"}},{"astro":{"sr":"06:51","ss":"17:04"},"cond":{"code_d":"100","code_n":"100","txt_d":"晴","txt_n":"晴"},"date":"2016-11-08","hum":"48","pcpn":"0.0","pop":"0","pres":"1034","tmp":{"max":"10","min":"0"},"vis":"10","wind":{"deg":"188","dir":"无持续风向","sc":"微风","spd":"2"}},{"astro":{"sr":"06:52","ss":"17:03"},"cond":{"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"},"date":"2016-11-09","hum":"57","pcpn":"0.0","pop":"0","pres":"1032","tmp":{"max":"11","min":"1"},"vis":"10","wind":{"deg":"192","dir":"无持续风向","sc":"微风","spd":"10"}},{"astro":{"sr":"06:53","ss":"17:02"},"cond":{"code_d":"101","code_n":"100","txt_d":"多云","txt_n":"晴"},"date":"2016-11-10","hum":"67","pcpn":"1.3","pop":"75","pres":"1029","tmp":{"max":"13","min":"1"},"vis":"10","wind":{"deg":"195","dir":"北风","sc":"3-4","spd":"14"}}]
     * hourly_forecast : [{"date":"2016-11-04 10:00","hum":"37","pop":"0","pres":"1013","tmp":"15","wind":{"deg":"130","dir":"东南风","sc":"微风","spd":"4"}},{"date":"2016-11-04 13:00","hum":"30","pop":"0","pres":"1010","tmp":"19","wind":{"deg":"154","dir":"东南风","sc":"微风","spd":"6"}},{"date":"2016-11-04 16:00","hum":"40","pop":"0","pres":"1008","tmp":"17","wind":{"deg":"175","dir":"南风","sc":"微风","spd":"7"}},{"date":"2016-11-04 19:00","hum":"50","pop":"0","pres":"1009","tmp":"14","wind":{"deg":"196","dir":"西南风","sc":"微风","spd":"5"}},{"date":"2016-11-04 22:00","hum":"53","pop":"0","pres":"1010","tmp":"12","wind":{"deg":"304","dir":"西北风","sc":"微风","spd":"3"}}]
     * now : {"cond":{"code":"501","txt":"雾"},"fl":"2","hum":"92","pcpn":"0","pres":"1011","tmp":"5","vis":"1","wind":{"deg":"60","dir":"东南风","sc":"3-4","spd":"16"}}
     * status : ok
     * suggestion : {"comf":{"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"},"cw":{"brf":"不宜","txt":"不宜洗车，未来24小时内有霾，如果在此期间洗车，会弄脏您的爱车。"},"drsg":{"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"},"flu":{"brf":"易发","txt":"昼夜温差大，且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"},"sport":{"brf":"较不宜","txt":"有扬沙或浮尘，建议适当停止户外运动，选择在室内进行运动，以避免吸入更多沙尘，有损健康。"},"trav":{"brf":"较不宜","txt":"空气质量差，不适宜旅游"},"uv":{"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}}
     */

    private String status;
    /**
     * comf : {"brf":"舒适","txt":"白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。"}
     * cw : {"brf":"不宜","txt":"不宜洗车，未来24小时内有霾，如果在此期间洗车，会弄脏您的爱车。"}
     * drsg : {"brf":"较冷","txt":"建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"}
     * flu : {"brf":"易发","txt":"昼夜温差大，且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。"}
     * sport : {"brf":"较不宜","txt":"有扬沙或浮尘，建议适当停止户外运动，选择在室内进行运动，以避免吸入更多沙尘，有损健康。"}
     * trav : {"brf":"较不宜","txt":"空气质量差，不适宜旅游"}
     * uv : {"brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"}
     */

    private Suggestion suggestion;
    /**
     * astro : {"sr":"06:47","ss":"17:08"}
     * cond : {"code_d":"502","code_n":"502","txt_d":"霾","txt_n":"霾"}
     * date : 2016-11-04
     * hum : 45
     * pcpn : 0.0
     * pop : 0
     * pres : 1011
     * tmp : {"max":"15","min":"4"}
     * vis : 10
     * wind : {"deg":"168","dir":"无持续风向","sc":"微风","spd":"6"}
     */

    private List<DailyForecast> daily_forecast;
    /**
     * date : 2016-11-04 10:00
     * hum : 37
     * pop : 0
     * pres : 1013
     * tmp : 15
     * wind : {"deg":"130","dir":"东南风","sc":"微风","spd":"4"}
     */

    private List<HourlyForecast> hourly_forecast;

    public Aqi getAqi() {
        return aqi;
    }

    public void setAqi(Aqi aqi) {
        this.aqi = aqi;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Suggestion getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(Suggestion suggestion) {
        this.suggestion = suggestion;
    }

    public List<DailyForecast> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecast> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public List<HourlyForecast> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(List<HourlyForecast> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public static class Aqi {
        /**
         * aqi : 250
         * co : 3
         * no2 : 104
         * o3 : 4
         * pm10 : 160
         * pm25 : 250
         * qlty : 重度污染
         * so2 : 4
         */

        private CityBean city;

        public CityBean getCity() {
            return city;
        }

        public void setCity(CityBean city) {
            this.city = city;
        }

        public static class CityBean {
            private String aqi;
            private String co;
            private String no2;
            private String o3;
            private String pm10;
            private String pm25;
            private String qlty;
            private String so2;

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String getPm25() {
                return pm25;
            }

            public void setPm25(String pm25) {
                this.pm25 = pm25;
            }

            public String getQlty() {
                return qlty;
            }

            public void setQlty(String qlty) {
                this.qlty = qlty;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }
    }

    public static class Basic {
        private String city;
        private String cnty;
        private String id;
        private String lat;
        private String lon;
        /**
         * loc : 2016-11-04 09:53
         * utc : 2016-11-04 01:53
         */

        private Update update;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public Update getUpdate() {
            return update;
        }

        public void setUpdate(Update update) {
            this.update = update;
        }

        public static class Update {
            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }
    }

    public static class Now {
        /**
         * code : 501
         * txt : 雾
         */

        private CondBean cond;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        /**
         * deg : 60
         * dir : 东南风
         * sc : 3-4
         * spd : 16
         */

        private WindBean wind;

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class CondBean {
            private String code;
            private String txt;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class WindBean {
            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class Suggestion {
        /**
         * brf : 舒适
         * txt : 白天不太热也不太冷，风力不大，相信您在这样的天气条件下，应会感到比较清爽和舒适。
         */

        private ComfBean comf;
        /**
         * brf : 不宜
         * txt : 不宜洗车，未来24小时内有霾，如果在此期间洗车，会弄脏您的爱车。
         */

        private CwBean cw;
        /**
         * brf : 较冷
         * txt : 建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。
         */

        private DrsgBean drsg;
        /**
         * brf : 易发
         * txt : 昼夜温差大，且空气湿度较大，易发生感冒，请注意适当增减衣服，加强自我防护避免感冒。
         */

        private FluBean flu;
        /**
         * brf : 较不宜
         * txt : 有扬沙或浮尘，建议适当停止户外运动，选择在室内进行运动，以避免吸入更多沙尘，有损健康。
         */

        private SportBean sport;
        /**
         * brf : 较不宜
         * txt : 空气质量差，不适宜旅游
         */

        private TravBean trav;
        /**
         * brf : 最弱
         * txt : 属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
         */

        private UvBean uv;

        public ComfBean getComf() {
            return comf;
        }

        public void setComf(ComfBean comf) {
            this.comf = comf;
        }

        public CwBean getCw() {
            return cw;
        }

        public void setCw(CwBean cw) {
            this.cw = cw;
        }

        public DrsgBean getDrsg() {
            return drsg;
        }

        public void setDrsg(DrsgBean drsg) {
            this.drsg = drsg;
        }

        public FluBean getFlu() {
            return flu;
        }

        public void setFlu(FluBean flu) {
            this.flu = flu;
        }

        public SportBean getSport() {
            return sport;
        }

        public void setSport(SportBean sport) {
            this.sport = sport;
        }

        public TravBean getTrav() {
            return trav;
        }

        public void setTrav(TravBean trav) {
            this.trav = trav;
        }

        public UvBean getUv() {
            return uv;
        }

        public void setUv(UvBean uv) {
            this.uv = uv;
        }

        public static class ComfBean {
            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class CwBean {
            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class DrsgBean {
            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class FluBean {
            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class SportBean {
            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class TravBean {
            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }

        public static class UvBean {
            private String brf;
            private String txt;

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }

    public static class DailyForecast {
        /**
         * sr : 06:47
         * ss : 17:08
         */

        private AstroBean astro;
        /**
         * code_d : 502
         * code_n : 502
         * txt_d : 霾
         * txt_n : 霾
         */

        private CondBean cond;
        private String date;
        private String hum;
        private String pcpn;
        private String pop;
        private String pres;
        /**
         * max : 15
         * min : 4
         */

        private TmpBean tmp;
        private String vis;
        /**
         * deg : 168
         * dir : 无持续风向
         * sc : 微风
         * spd : 6
         */

        private WindBean wind;

        public AstroBean getAstro() {
            return astro;
        }

        public void setAstro(AstroBean astro) {
            this.astro = astro;
        }

        public CondBean getCond() {
            return cond;
        }

        public void setCond(CondBean cond) {
            this.cond = cond;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public TmpBean getTmp() {
            return tmp;
        }

        public void setTmp(TmpBean tmp) {
            this.tmp = tmp;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class AstroBean {
            private String sr;
            private String ss;

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }
        }

        public static class CondBean {
            private String code_d;
            private String code_n;
            private String txt_d;
            private String txt_n;

            public String getCode_d() {
                return code_d;
            }

            public void setCode_d(String code_d) {
                this.code_d = code_d;
            }

            public String getCode_n() {
                return code_n;
            }

            public void setCode_n(String code_n) {
                this.code_n = code_n;
            }

            public String getTxt_d() {
                return txt_d;
            }

            public void setTxt_d(String txt_d) {
                this.txt_d = txt_d;
            }

            public String getTxt_n() {
                return txt_n;
            }

            public void setTxt_n(String txt_n) {
                this.txt_n = txt_n;
            }
        }

        public static class TmpBean {
            private String max;
            private String min;

            public String getMax() {
                return max;
            }

            public void setMax(String max) {
                this.max = max;
            }

            public String getMin() {
                return min;
            }

            public void setMin(String min) {
                this.min = min;
            }
        }

        public static class WindBean {
            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }

    public static class HourlyForecast {
        private String date;
        private String hum;
        private String pop;
        private String pres;
        private String tmp;
        /**
         * deg : 130
         * dir : 东南风
         * sc : 微风
         * spd : 4
         */

        private WindBean wind;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
        }

        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
        }

        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
        }

        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public static class WindBean {
            private String deg;
            private String dir;
            private String sc;
            private String spd;

            public String getDeg() {
                return deg;
            }

            public void setDeg(String deg) {
                this.deg = deg;
            }

            public String getDir() {
                return dir;
            }

            public void setDir(String dir) {
                this.dir = dir;
            }

            public String getSc() {
                return sc;
            }

            public void setSc(String sc) {
                this.sc = sc;
            }

            public String getSpd() {
                return spd;
            }

            public void setSpd(String spd) {
                this.spd = spd;
            }
        }
    }
}
