package net.endyingjie.ui_desgin.reader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class FolderAssetsReader{
    protected ArrayList<File> files = new ArrayList<>();
    protected FolderAssetsReader(){
        File file = new File(getFolder());
        if(file.mkdir()){
            files.addAll(Arrays.asList(file.listFiles()));
        }
    }
    protected abstract String getFolder();

}
