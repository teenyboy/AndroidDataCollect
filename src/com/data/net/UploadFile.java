package com.data.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.data.configure.Constants;
import com.data.db.DBDataBase;
import com.data.file.FileList;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wang on 15-1-18.
 */
public class UploadFile {

    private final static String TAG = "UploadFile";
    private final static int TIME_OUT = 10 * 10000000;
    private final static String CHARSET = "UTF-8";
    private final static String urlString = "http://192.168.1.11:8080";
    private final static String PREFIX = "--";
    private final static String LINE_END = "\r\n";
    Context context;
    private ArrayList<FileList> files;
    private BlockingQueue<Runnable> blockingQueue;
    private ThreadPoolExecutor threadPoolExecutor;
    private Cursor c;
    private int line = 0;
    //防止id不从1开始
    private int dataBaseCount = 1;
    private int id;
    private int first_id;

    private SQLiteDatabase sqLiteDatabase;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    //断点文件
    private int fileContinueNum;
    //断点文件位置
    private int schudule_length;

    public UploadFile(Context context) {
        this.context = context;

        sqLiteDatabase = DBDataBase.getDBDataBase(context);

        sharedPreferences = context.getSharedPreferences(Constants.UPLOAD_SCHEDULE, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //获得断点文件
        fileContinueNum = sharedPreferences.getInt(Constants.FILENUM, 0);
        Log.d(TAG, "fileContinueNum is " + String.valueOf(fileContinueNum));
        //获得断点文件传输具体位置
        schudule_length = sharedPreferences.getInt(Constants.SCHEDULE, 0);
        Log.d(TAG, "schudule_length is " + String.valueOf(schudule_length));

        files = new ArrayList<FileList>();

        c = DBDataBase.getDBCursor(sqLiteDatabase);

        //移到首项，读取首项id
        c.moveToFirst();

        first_id = c.getInt(c.getColumnIndex("id"));
        if (first_id != 1) {
            sqLiteDatabase.execSQL("UPDATE FileData SET id = id - " + (first_id - 1));
        }

        //移到首项，准备挨个读取数据
        c.moveToFirst();

        while (!c.isLast()) {

            if (dataBaseCount == c.getInt(c.getColumnIndex("id"))) {
                id = c.getInt(c.getColumnIndex("id"));
            } else {
                id = dataBaseCount;
            }

            //数据库最后一排为今日数据，不上传，所以排出fileList中

            if (id != c.getCount()) {
                FileList fileList = new FileList();
                fileList.configFileString = c.getString(c.getColumnIndex("configFile"));
                fileList.appFileString = c.getString(c.getColumnIndex("appFile"));
                fileList.usrStateFileString = c.getString(c.getColumnIndex("usrStateFile"));
                fileList.browserBookmarkFileString = c.getString(c.getColumnIndex("browserBookmarkFile"));
                fileList.browserHistoryFileString = c.getString(c.getColumnIndex("browserHistoryFile"));
                fileList.phoneFileString = c.getString(c.getColumnIndex("phoneFile"));
                files.add(id - 1, fileList);
            }

            dataBaseCount++;
            c.moveToNext();
        }

    }

    public void uploadFile() {
        Iterator iterator = files.iterator();

        //计算需要多少队列，针对文件上传个数
        int FileCount = files.size() * 6;

        if (FileCount != 0) {
            blockingQueue = new LinkedBlockingQueue<Runnable>();
            threadPoolExecutor = new ThreadPoolExecutor(1, FileCount, 1, TimeUnit.DAYS, blockingQueue);

            while (iterator.hasNext()) {
                FileList fileList = (FileList) iterator.next();
                ArrayList<String> filePathList = fileList.getFileList();

                for (int j = 0; j < 6; j++) {
                    threadPoolExecutor.execute(new Thread(new UpLoad(filePathList.get(j), j)));
                }
            }
        }
    }

    public synchronized void upload(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            String END = "\r\n";
            String PREFIX = "--";
            String BOUNDARY = UUID.randomUUID().toString();  //边界标识   随机生成
            String CONTENT_TYPE = "multipart/form-data";   //内容类型
            InputStream inputStream = null;
            DataOutputStream dos = null;
            HttpURLConnection conn;
            try {
                URL url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();
                                 /*设置时间*/
                conn.setReadTimeout(10 * 1000);
                conn.setConnectTimeout(10 * 1000);

                                 /*允许Input,output,不使用Cache*/
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setUseCaches(false);

                                 /*设置传送的method=POST*/
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);

                                 /* 设置DataOutputStream */
                dos = new DataOutputStream(conn.getOutputStream());

                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(END);
                /**
                 * 这里重点注意：
                 * name里面的值为服务器端需要key   只有这个key 才可以得到对应的文件
                 * filename是文件的名字，包含后缀名的   比如:abc.png
                 */
                // Log.d(TAG, "the file name is " + file.getName());
                sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + END);
                sb.append("Content-Type: application/x-zip-compressed; charset=utf-8" + END);
                sb.append(END);
                dos.write(sb.toString().getBytes());

                                 /*取得文件的FileInputStream*/
                //    fileStream = new FileInputStream(file);

                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                if (fileContinueNum == line) {
                    Log.d(TAG, "get Breakpoint file");
                    if(schudule_length <0){
                        schudule_length = 0;
                    }
                    raf.seek(schudule_length);
                }
                /*设置每次写入1024bytes*/
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length;
                int count = 0;

                /*从文件读取数据至缓冲区*/
                while ((length = raf.read(buffer)) != -1) {
                    dos.write(buffer, 0, length);
                    count++;
                }

                length = count * 1024 + length;

                //存取读取多少bytes
                Log.d(TAG, "The length is " + length);

                editor.putInt(Constants.SCHEDULE, length);
                editor.commit();

                dos.write(END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + END).getBytes();
                dos.write(end_data);
                dos.flush();

                                 /* 取得Response内容 */
                InputStream is = conn.getInputStream();
                int ch;
                StringBuffer b = new StringBuffer();
                while ((ch = is.read()) != -1) {
                    b.append((char) ch);
                }
                String result = b.toString();
                Log.d(TAG, result);
                result = result.substring(2,6);
                Log.d(TAG, result);
                //删除已上传文件
                if (result.equals("file")) {
                    file.delete();
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.d(TAG, "file not exist");
        }
        if (!file.exists()) {
            line++;

            editor.putInt(Constants.FILENUM, line % 6);

            int dataBaseDeleteFlag = line % 6;
            if (dataBaseDeleteFlag == 0) {
                sqLiteDatabase.execSQL("DELETE FROM FileData WHERE id = 1");
                //数据库更新id位置
                sqLiteDatabase.execSQL("UPDATE FileData SET id = id -1");
                Log.d(TAG, "dataBaseUpdate");
            }
        }
    }

    private class UpLoad implements Runnable {
        //上传文件路径
        private String fileUploadPath;
        //文件位置
        private int FileNum;

        public UpLoad(String filePath, int fileNum) {
            fileUploadPath = filePath;

            this.FileNum = fileNum;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                upload(fileUploadPath);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //   Log.d(TAG, uploadFilePath);
        }
    }
}



