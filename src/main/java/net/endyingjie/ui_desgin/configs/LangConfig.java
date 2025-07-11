package net.endyingjie.ui_desgin.configs;

public enum LangConfig {
    Chinese,English;
    @Override
    public String toString(){
        switch (this){
            case Chinese:
                return "中文";
            case English:
                return "English";
            default:
                return  "Error";
        }
    }
}
