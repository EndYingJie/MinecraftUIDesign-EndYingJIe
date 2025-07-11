package net.endyingjie.ui_desgin.resources;

import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.configs.Config;
import java.util.logging.Logger;

public final class LangReading {
    public enum Model{LANG,CONTENT;
    @Override
    public String toString(){
        if(this==LANG){
            return "lang_model";
        }else if(this==CONTENT){
            return "content_model";
        }else {
            return "content_model";
        }
    }}
    private final JSONObject LangContent;
    private final JSONObject LangContent2;
    private final Model model;
    private static final FSResourceReading readingFolder = FSResourceReading.create("lang");
    private LangReading(Model model){
        this.model =model;
        if(LangReading.readingFolder.getFile(Config.lang.getLanguage().concat("_").concat(Config.lang.getCountry().toLowerCase()).concat(".json")).getContent()!=null){
            LangContent = JSONObject.parseObject(LangReading.readingFolder.getFile(Config.lang.getLanguage().concat("_").concat(Config.lang.getCountry().toLowerCase()).concat(".json")).getContent());
            LangContent2 = JSONObject.parseObject(ResourceReading.create("lang/en_us.json").getContent());
        }else {
            LangContent =new JSONObject();
            LangContent2=JSONObject.parseObject(ResourceReading.create("lang/en_us.json").getContent());
        }
    }

    private static final Logger logger= Logger.getLogger("Language Reading");
    public String getContent(String key_content) {
        if(model==Model.LANG && LangContent!=null){
            if(!LangContent.isEmpty()) {
                if (LangContent.containsKey(key_content))
                    return LangContent.getString(key_content);
                else {
                    if(LangContent2.containsKey(key_content)){
                        logger.warning("Not Found Locale Language Key");
                        return LangContent2.getString(key_content);
                    }else{
                        logger.warning("Not Found Language Key");
                        return key_content;
                    }
                }
            }
                else{
                    logger.warning("Not Found Locale Language File");
                    return LangContent2.getString(key_content);
                }

        }else if(model==Model.CONTENT){
            return key_content;
        }else{
            return key_content;
        }
    }

    public static LangReading create(Model model){
        return new LangReading(model);
    }

    public Model getModel(){
        return model;
    }

}
