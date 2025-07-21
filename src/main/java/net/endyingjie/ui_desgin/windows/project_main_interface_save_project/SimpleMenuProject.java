package net.endyingjie.ui_desgin.windows.project_main_interface_save_project;

import com.alibaba.fastjson2.JSONObject;
import net.endyingjie.ui_desgin.manager.SlotGroupManage;
import net.endyingjie.ui_desgin.manager.SlotManager;
import net.endyingjie.ui_desgin.windows.jdialog_windows.config_windows.VersionForge;

public final class SimpleMenuProject implements MenuProject{
    private final String name;
    private final String image_path;
    private final SlotManager manager;
    private final VersionForge version;
    private final boolean isModel;
    private final SlotGroupManage slotGroup;
    private final int textureX;
    private final int textureY;
    public SimpleMenuProject(String name, String image_path, SlotManager manager, VersionForge version, boolean isModel, SlotGroupManage slotGroup,int textureX,int textureY){
        this.name=name;
        this.image_path = image_path;
        this.manager= manager;
        this.version =version;
        this.isModel = isModel;
        this.slotGroup =slotGroup;
        this.textureX=textureX;
        this.textureY=textureY;
    }
    @Override
    public boolean isModel(){
        return isModel;
    }
    @Override
    public VersionForge getVersion(){
        return this.version;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getImagePath() {
        return this.image_path;
    }

    @Override
    public SlotManager getSlotManager() {
        return this.manager;
    }
    @Override
    public SlotGroupManage getSlotGroups(){
        return slotGroup;
    }

    @Override
    public int getTextureX() {
        return this.textureX;
    }

    @Override
    public int getTextureY() {
        return this.textureY;
    }

    public static SimpleMenuProject of(JSONObject json){
        String imagePath = json.getString("main_image_path");
        VersionForge version =VersionForge.of(json.getString("version"));
        String name = json.getString("name");
        SlotManager slotManager = SlotManager.fromJson(json.getJSONArray("slots"));
        boolean isModel = json.getBooleanValue("is_model");
        SlotGroupManage slotGroupManage = SlotGroupManage.fromJson(json.getJSONArray("slot_group"));
        int textureY = json.getIntValue("texture_x");
        int textureX= json.getIntValue("texture_y");
        return new SimpleMenuProject(name,imagePath,slotManager,version,isModel,slotGroupManage,textureX,textureY);
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        json.put("main_image_path",this.getImagePath());
        json.put("version",this.getVersion().toString());
        json.put("name",this.getName());
        json.put("slots",this.getSlotManager().toJson());
        json.put("project_type",this.getType().toString());
        json.put("is_model",this.isModel());
        json.put("slot_group",this.slotGroup.toJson());
        json.put("texture_x",this.textureX);
        json.put("texture_y",this.textureY);
        return json;
    }
}
