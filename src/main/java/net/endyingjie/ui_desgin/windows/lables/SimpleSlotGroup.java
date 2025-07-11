package net.endyingjie.ui_desgin.windows.lables;

import com.alibaba.fastjson2.JSONObject;


public final class SimpleSlotGroup  implements SlotGroupInformation{
    private final int x;
    private final int y;
    private final int weightSlot;
    private final int heightSlot;
    private final int index;
    public SimpleSlotGroup(int x,int y,int weightSlot,int heightSlot,int index){
        this.x =x;
        this.y =y;
        this.weightSlot = weightSlot;
        this.heightSlot = heightSlot;
        this.index = index;
    }
    public SimpleSlotGroup(SlotGroupInformation slotGroupInformation){
        this.x =slotGroupInformation.getCX();
        this.y =slotGroupInformation.getCY();
        this.weightSlot = slotGroupInformation.getWeightSlot();
        this.heightSlot = slotGroupInformation.getHeightSlot();
        this.index = slotGroupInformation.getIndex();
    }
    public static SimpleSlotGroup fromJson(JSONObject json){
        int weight = json.getIntValue("weight");
        int height = json.getIntValue("height");
        int lessIndex = json.getIntValue("index");
        int x = json.getIntValue("slot_group_x");
        int y = json.getIntValue("slot_group_y");
        return new SimpleSlotGroup(x,y,weight,height,lessIndex);
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("weight",this.weightSlot);
        json.put("height",this.heightSlot);
        json.put("index",this.index);
        json.put("slot_group_x",this.x);
        json.put("slot_group_y",this.y);
        return json;
    }
    @Override
    public int getCX() {
        return this.x;
    }

    @Override
    public int getCY() {
        return this.y;
    }

    @Override
    public int getWeightSlot() {
        return this.weightSlot;
    }

    @Override
    public int getHeightSlot() {
        return this.heightSlot;
    }

    @Override
    public int getIndex() {
        return this.index;
    }

}
