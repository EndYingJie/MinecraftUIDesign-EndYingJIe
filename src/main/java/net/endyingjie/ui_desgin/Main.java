package net.endyingjie.ui_desgin;

import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.configs.Config;
import net.endyingjie.ui_desgin.configs.LangConfig;
import net.endyingjie.ui_desgin.windows.MainWindows;
import net.endyingjie.ui_desgin.windows.other.LoadingWindow;
import net.endyingjie.ui_desgin.write.ConfigWriter;
import net.endyingjie.ui_desgin.write.SoftwareIntroductionWriter;

import javax.swing.*;
import java.io.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static net.endyingjie.ui_desgin.write.ConfigWriter.reader;


public final class Main {
    public static final LoadingWindow loadWindow=new LoadingWindow();
    public static String language;
    public static  String country;
    public static boolean introduce=true;
    private static Logger logger = Logger.getLogger("Config Information");
    public static void main(String[] args){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("MUIDesignConfig.json"));
            String con;
            StringBuffer buffer = new StringBuffer();
            while((con=reader.readLine())!=null){
                buffer.append(con);
            }
            boolean isRightOrError = true;
            con=buffer.toString();
            logger.info("Config read is right");
            JSONObject json = JSONObject.parseObject(con);
            if(json!=null) {
                if (json.containsKey("lang")) {
                    if(json.getString("lang").length()==2){
                        language = json.getString("lang");
                        logger.config("The Language is ".concat(language));
                    }else {
                        logger.config("The lang key is not Language information");
                        json.put("lang", "zh");
                        if(json.containsKey("country")){
                            json.replace("country","zh");
                        }else json.put("country","zh");
                        isRightOrError = false;
                        language = "zh";
                        country = "CN";
                    }
                } else {
                    logger.config( "Can't Found Lang Key");
                    json.put("lang", "zh");
                    isRightOrError = false;
                    if(json.containsKey("lang")){
                        json.replace("lang","zh");
                    }else json.put("lang","zh");
                    language = "zh";
                    country = "CN";
                }
                if (json.containsKey("country")) {
                    if(json.getString("country").length()==2){
                        country = json.getString("country");
                        logger.config("The Country is ".concat(country));
                    }else {
                        logger.log(Level.WARNING, "The country key is not country information");
                        json.put("country", "CN");
                        isRightOrError = false;
                        language = "zh";
                        country = "CN";
                    }
                } else {
                    logger.log(Level.WARNING, "Can't Found Country Key");
                    json.put("country", "CN");
                    isRightOrError = false;
                    language = "zh";
                    country = "CN";
                }
                if(!isRightOrError){
                    FileWriter fileWriter = new FileWriter("MUIDesignConfig.json");
                    fileWriter.append(json.toJSONString());
                    fileWriter.close();
                }
            }else {
                logger.log(Level.WARNING,"load config json content is Error");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("lang","zh");
                jsonObject.put("country","CN");
                FileWriter ConfigWriter= new FileWriter("MUIDesignConfig.json");
                ConfigWriter.append(jsonObject.toJSONString());
                ConfigWriter.close();
                language="zh";
                country="CN";
            }
        } catch (IOException e) {
            logger.log(Level.WARNING,"Can't read the config file");
            try {
              FileWriter writer = new FileWriter("MUIDesignConfig.json");
              JSONObject jsonObject = new JSONObject();
              jsonObject.put("lang","zh");
              jsonObject.put("country","CN");
              writer.append(jsonObject.toJSONString());
              writer.close();
            } catch (IOException ex) {
                logger.warning("Can't write config file");
            }
            language="zh";
            country="CN";
        }
        Config.lang=new Locale(Main.language,Main.country);
        try{
            reader = new BufferedReader(new FileReader("MUIDesignConfig.json"));
        } catch (FileNotFoundException e) {
            logger.warning("Not find config File");
        }
        try{
            String content;
            StringBuffer temp = new StringBuffer();
            while((content = reader.readLine())!=null){
                temp.append(content);
            }
            content = temp.toString();
            ConfigWriter.content = content;
        } catch (IOException e) {
            ConfigWriter.content = "{}";
            Logger.getLogger("Config Writer").warning("Can't read file content");
        }
        ConfigWriter.jsonObject2=JSONObject.parseObject(ConfigWriter.content);
        switch (Config.lang.getLanguage().concat("_").concat(Config.lang.getCountry().toLowerCase())){
            case "zh_cn":
                Config.lang_enum= LangConfig.Chinese;
                break;
            case "en_us":
                Config.lang_enum = LangConfig.English;
                break;
            default:
                Config.lang_enum= LangConfig.Chinese;
                break;
        }
       if(reader==null){
           Main.introduce =true;
           logger.warning("introduce don't know read or not read");
       }else {
           Main.introduce = new SoftwareIntroductionWriter().read();
           logger.info("introduce know read");
       }
       mainWindows=new MainWindows();
       JOptionPane.setDefaultLocale(Config.lang);
    }
    public static MainWindows mainWindows;
}
