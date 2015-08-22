package com.data.file;

import java.util.ArrayList;

/**
 * Created by root on 15-1-25.
 */
public class FileList {

    public String configFileString;
    public String appFileString;
    public String usrStateFileString;
    public String browserBookmarkFileString;
    public String browserHistoryFileString;
    public String phoneFileString;

    public ArrayList<String> getFileList() {
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add(0, configFileString);
        arrayList.add(1, appFileString);
        arrayList.add(2, usrStateFileString);
        arrayList.add(3, browserBookmarkFileString);
        arrayList.add(4, browserHistoryFileString);
        arrayList.add(5, phoneFileString);
        return arrayList;
    }

}
