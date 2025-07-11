package net.endyingjie.ui_desgin.manager;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.manager.interface_manager.SimpleSlotInformation;
import net.endyingjie.ui_desgin.manager.interface_manager.SlotInformation;

import java.util.ArrayList;
import java.util.Arrays;


public class SlotManager {
    protected final ArrayList<SlotInformation> allSlots = new ArrayList<>();
    public SlotManager(int number,SlotInformation... slots){
        allSlots.addAll(Arrays.asList(slots).subList(0, number));
    }
    public ArrayList<SlotInformation> getSlots(){
        return allSlots;
    }
    public SlotManager(ArrayList<SlotInformation> slots){
        allSlots.addAll(slots);
    }
    public SlotInformation getSlot(int index){
        for(SlotInformation slot:allSlots){
            if(slot.getIndex()==index){
                return slot;
            }
        }
        return SimpleSlotInformation.EMPTY;
    }
    public void addSlot(SlotInformation slot){
        allSlots.add(slot);
    }
    public static SlotManager fromJson(JSONArray json){
        ArrayList<SlotInformation> slots = new ArrayList<>();
            for (int i = 0; i < json.size(); ++i) {
                slots.add(SimpleSlotInformation.JsonOf(json.getJSONObject(i)));
            }
        return new SlotManager(slots);
    }
    public JSONArray toJson(){
        return new JSONArray(this.allSlots);
    }
}
