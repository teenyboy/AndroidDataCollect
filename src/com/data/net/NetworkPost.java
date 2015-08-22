package com.data.net;

import android.util.Log;

import com.data.configure.ConnectMessage;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by wang on 15-1-1.
 */
public class NetworkPost {

    private final static String TAG = "NetworkPost";

    public static ConnectMessage Post(String url, String data) {
        Log.d(TAG, "connectMessage post");

        String connectReturn = "";
        ConnectMessage connectMessage = new ConnectMessage();

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            StringEntity se = new StringEntity("content" + data, HTTP.UTF_8);
            Log.d(TAG, "content" + data);
            se.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(se);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int status = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "http status is" + status);
            String returnXml = EntityUtils.toString(httpResponse.getEntity());
            connectReturn = URLDecoder.decode(returnXml);
            switch (status) {
                case 200:
                    connectMessage.setConnectFlag(true);
                    connectMessage.setConnectMessage(connectReturn);
                    break;
                default:
                    Log.d(TAG, "error" + status + connectReturn);
                    connectMessage.setConnectFlag(false);
                    connectMessage.setConnectMessage(connectReturn);
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            connectMessage.setConnectFlag(false);
            connectMessage.setConnectMessage(e.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            connectMessage.setConnectFlag(false);
            connectMessage.setConnectMessage(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            connectMessage.setConnectFlag(false);
            connectMessage.setConnectMessage(e.toString());
        }
        return connectMessage;
    }

}
