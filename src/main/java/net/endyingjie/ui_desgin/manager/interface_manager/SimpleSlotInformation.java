package net.endyingjie.ui_desgin.manager.interface_manager;

import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.windows.project_main.SlotType;

public final class SimpleSlotInformation implements SlotInformation{
    public static final SlotInformation EMPTY  =new SimpleSlotInformation(0,0,-1,null);
    private final int x;
    private final int y;
    private final int index;
    private final SlotType type;
    public SimpleSlotInformation(int x,int y,int index,SlotType type){
        this.x=x;
        this.y=y;
        this.index=index;
        this.type = type;
    }
    @Override
    public SlotType getType(){
        return type;
    }
    @Override
    public int getSlotX() {
        return this.x;
    }

    @Override
    public int getSlotY() {
        return this.y;
    }

    @Override
    public int getIndex() {
        return this.index;
    }
    public static SimpleSlotInformation JsonOf(JSONObject json){
        int x= json.getIntValue("slotX");
        int y = json.getIntValue("slotY");
        int index =json.getIntValue("index");
        SlotType type = SlotType.valueOf(json.getString("type"));
        return new SimpleSlotInformation(x,y,index,type);
    }
    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("slotX",x);
        jsonObject.put("slotY",y);
        jsonObject.put("index",index);
        jsonObject.put("type",type);
        return jsonObject;

    }
}
