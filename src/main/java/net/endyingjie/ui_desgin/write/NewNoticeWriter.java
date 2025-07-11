package net.endyingjie.ui_desgin.write;

import net.endyingjie.ui_desgin.configs.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public final class NewNoticeWriter extends CConfigWriter{
    public NewNoticeWriter(){
        super();
    }
    public void write(){
        if(loadOk){
            try {
                FileWriter fileWriter = new FileWriter(CConfigWriter.configPath);
                if (jsonObject.containsKey("new_notice".concat(Config.TheVersion))) {
                    jsonObject.replace("new_notice".concat(Config.TheVersion), true);
                } else jsonObject.put("new_notice".concat(Config.TheVersion), true);
                fileWriter.append(jsonObject.toJSONString());
                fileWriter.close();
            } catch (IOException e) {
                this.logger.warning("can't write config file");
            }
        }
    }
    public boolean read(){
        if(loadOk&&this.readIs){
           return jsonObject.containsKey("new_notice".concat(Config.TheVersion)) && jsonObject.getBooleanValue("new_notice".concat(Config.TheVersion));
        }else return true;
    }
    @Override
    protected Logger getLogger() {
        return Logger.getLogger("NewNotice Writer");
    }
}
