package net.endyingjie.ui_desgin.windows.project_main;

import java.lang.reflect.Type;

public final class SimpleType implements Type {
    private final String name;
    public SimpleType(String name){
        this.name =name;
    }
    @Override
    public String getTypeName(){
        return name;
    }
}
