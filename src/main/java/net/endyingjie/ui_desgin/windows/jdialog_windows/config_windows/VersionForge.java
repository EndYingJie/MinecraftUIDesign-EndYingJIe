package net.endyingjie.ui_desgin.windows.jdialog_windows.config_windows;

public enum VersionForge {
    K1$20$1_1$21$;
        @Override
        public String toString(){
            switch (this){
                case K1$20$1_1$21$:
                    return "1.20.1-1.21";
                default:
                    return "1.20.1-1.21";
            }
        }
        private static void changeVersion(VersionForge change,VersionForge mu_bi){
            change=mu_bi;
        }
        public void setVersion(VersionForge version){
            changeVersion(this,version);
        }
        public static VersionForge of(String name){
            switch (name){
                case "1.20.1-1.21":
                    return K1$20$1_1$21$;
                default:
                    return K1$20$1_1$21$;
            }
        }
}
