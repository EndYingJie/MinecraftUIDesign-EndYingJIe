package net.endyingjie.ui_desgin.manager;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.windows.lables.SimpleSlotGroup;
import net.endyingjie.ui_desgin.windows.lables.SlotGroupLabel;
import net.endyingjie.ui_desgin.windows.lables.SlotGroupInformation;

import java.util.ArrayList;
import java.util.Arrays;

public class SlotGroupManage {
    protected final ArrayList<SlotGroupInformation> allSlots = new ArrayList<>();
    public SlotGroupManage(int number, SlotGroupLabel... slots){
        allSlots.addAll(Arrays.asList(slots).subList(0, number));
    }
    public ArrayList<SlotGroupInformation> getSlots(){
        return allSlots;
    }
    public SlotGroupManage( ArrayList<SlotGroupInformation> slots){
        allSlots.addAll(slots);
    }

    public void addSlot(SlotGroupInformation slot){
        allSlots.add(slot);
    }
    public static SlotGroupManage fromJson(JSONArray json){
        ArrayList<SlotGroupInformation> slots = new ArrayList<SlotGroupInformation>();
        for (int i = 0; i < json.size(); ++i) {
            slots.add(SimpleSlotGroup.fromJson(json.getJSONObject(i)));
        }
        return new SlotGroupManage(slots);
    }
    public JSONArray toJson(){
        JSONArray jsonArray = new JSONArray();
        for(SlotGroupInformation slot:this.allSlots){
            JSONObject json = jsonArray.addObject();
            json.put("weight",slot.getWeightSlot());
            json.put("height",slot.getHeightSlot());
            json.put("index",slot.getIndex());
            json.put("slot_group_x",slot.getCX());
            json.put("slot_group_y",slot.getCY());
        }
        return jsonArray;
    }
}
