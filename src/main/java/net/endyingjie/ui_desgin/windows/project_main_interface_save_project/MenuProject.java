package net.endyingjie.ui_desgin.windows.project_main_interface_save_project;

import net.endyingjie.ui_desgin.manager.SlotGroupManage;
import net.endyingjie.ui_desgin.manager.SlotManager;
import net.endyingjie.ui_desgin.windows.jdialog_windows.config_windows.VersionForge;
import net.endyingjie.ui_desgin.windows.project_main.ProjectTypes;

public interface MenuProject {
    String getName();
    String getImagePath();
    SlotManager getSlotManager();
    VersionForge getVersion();
    boolean isModel();
    SlotGroupManage getSlotGroups();
    default ProjectTypes getType(){
        return ProjectTypes.MenuProject;
    }
}
