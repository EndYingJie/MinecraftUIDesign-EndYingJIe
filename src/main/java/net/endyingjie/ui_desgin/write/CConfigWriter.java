package net.endyingjie.ui_desgin.write;

import com.alibaba.fastjson2.JSONObject;

import java.io.*;
import java.util.logging.Logger;

public abstract class CConfigWriter {
    private BufferedReader render;
    protected Logger logger;
    protected boolean readIs = true;
    protected boolean loadOk = false;
    protected  JSONObject jsonObject;
    public static final String configPath = "C:\\Program Files\\Minecraft UI Design EndYingJie\\GlobalConfig.json";
    protected CConfigWriter(){
        String con="{}";
        StringBuffer temp = new StringBuffer();
        try{
            logger = getLogger();
            File folder = new File("C:\\Program Files\\Minecraft UI Design EndYingJie");
            folder.mkdir();
            if(new File("C:\\Program Files\\Minecraft UI Design EndYingJie\\GlobalConfig.json").createNewFile()){
                FileWriter file = new FileWriter("C:\\Program Files\\Minecraft UI Design EndYingJie\\GlobalConfig.json");
                file.append("{}");
                file.close();
            }
            render = new BufferedReader(new FileReader("C:/Program Files/Minecraft UI Design EndYingJie/GlobalConfig.json"));
            while((con = render.readLine())!=null){
                temp.append(con);
            }
            con = temp.toString();
            jsonObject = JSONObject.parseObject(con);
            if(jsonObject==null){
                jsonObject=new JSONObject();
            }
        } catch (IOException e) {
            logger.warning("Can't read config file");
            e.printStackTrace();
            jsonObject = new JSONObject();
            render = null;
            readIs =false;
        }
        loadOk=true;
    }
    protected abstract Logger getLogger();
}
