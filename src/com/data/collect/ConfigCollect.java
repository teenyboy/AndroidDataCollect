package com.data.collect;

import android.content.Context;
import android.util.Log;

import com.data.file.FileState;
import com.data.file.ThreadFile;
import com.data.info.CollectDatas;
import com.data.main.CollectService;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by wang on 15-1-9.
 */
public class ConfigCollect {

    private static final String TAG = "ConfigCollect";
    Context context;
    CollectDatas collectDatas;


    public ConfigCollect(Context context) {
        this.context = context;
        collectDatas = new CollectDatas(context);
        if (collectDatas.getNoChangeCollectDatasMap()) {
      //      Log.d(TAG, "get configDatas");
        }

    }

    public String getConfigCollect() {

        StringBuffer sb = new StringBuffer();

        Set set = collectDatas.nochange_infoMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            sb.append(mapEntry.getKey() + ":" + mapEntry.getValue() + "\n");
        }
        return String.valueOf(sb);
    }

    public void getConfigDatas() {

        String configData = getConfigCollect();
        ThreadFile tf = new ThreadFile(configData, FileState.configFile);

        FileState fs = new FileState();
        fs.deleteConfigFile();
        fs.makeConfigFile();

        CollectService.handler.post(tf);
    }
}
