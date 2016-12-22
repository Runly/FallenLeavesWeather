package com.ranli.fallenleavesweather.model;

/**
 * Created by ranly on 2016/12/22.
 */

public class Fir {

    /**
     * name : 落叶天气
     * version : 7
     * changelog : 不再使用org.json,改用Gson进行Json解析

     不再使用Volley进行网络请求，改用Retrofit

     删除不再使用的Model，重构部分代码
     * updated_at : 1478253449
     * versionShort : 1.4.0
     * build : 7
     * installUrl : http://download.fir.im/v2/app/install/57eea9b5ca87a86d59000c62?download_token=1d44f0e1fbc20d7963d341e0fa936f45&source=update
     * install_url : http://download.fir.im/v2/app/install/57eea9b5ca87a86d59000c62?download_token=1d44f0e1fbc20d7963d341e0fa936f45&source=update
     * direct_install_url : http://download.fir.im/v2/app/install/57eea9b5ca87a86d59000c62?download_token=1d44f0e1fbc20d7963d341e0fa936f45&source=update
     * update_url : http://fir.im/leavesweather
     * binary : {"fsize":2814661}
     */

    public String name;
    public String version;
    public String changelog;
    public int updated_at;
    public String versionShort;
    public String build;
    public String installUrl;
    public String install_url;
    public String direct_install_url;
    public String update_url;
    public Binary binary;

    public static class Binary {
        /**
         * fsize : 2814661
         */

        public int fsize;
    }
}
