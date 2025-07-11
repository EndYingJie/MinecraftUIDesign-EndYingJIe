package net.endyingjie.ui_desgin.write;

import com.alibaba.fastjson2.JSONObject;

import java.io.BufferedReader;
import java.util.logging.Logger;

public abstract class ConfigWriter {
   public static BufferedReader reader=null;
   public static String content;
   protected final Logger logger;
   protected String config_file = "MUIDesignConfig.json";
   public static JSONObject jsonObject2;
    protected final JSONObject jsonObject;
   protected ConfigWriter(){
       logger=getLogger();
       jsonObject = new JSONObject(jsonObject2);
   }
   protected abstract Logger getLogger();
}
