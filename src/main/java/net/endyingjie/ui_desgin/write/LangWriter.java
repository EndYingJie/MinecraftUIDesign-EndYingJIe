package net.endyingjie.ui_desgin.write;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public final class LangWriter extends ConfigWriter{
    public LangWriter(){
        super();
    }
    @Override
    public Logger getLogger() {
        return Logger.getLogger("LangWriter Information");
    }
    public void write(String lang,String country){
        try{
            FileWriter file = new FileWriter(this.config_file);
            if(jsonObject.containsKey("lang")){
                jsonObject.replace("lang",lang);
            }else jsonObject.put("lang",lang);
            if(jsonObject.containsKey("country")){
                jsonObject.replace("country",country);
            }else jsonObject.put("country",country);
            file.append(jsonObject.toJSONString());
            file.close();
            logger.info(jsonObject.getString("lang").concat("_").concat("country").concat("Language write is right"));
        } catch (IOException e) {
            this.logger.warning("can't use config file");
        }
    }
}
