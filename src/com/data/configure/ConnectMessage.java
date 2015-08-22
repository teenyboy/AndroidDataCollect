package com.data.configure;

/**
 * Created by wang on 15-1-3.
 */
//HTTP连接信息
public class ConnectMessage {

    private boolean connectFlag = false;
    private String connectMessage;

    public void setConnectFlag(boolean connectFlag) {
        this.connectFlag = connectFlag;
    }

    public boolean getConnectFlag() {
        return connectFlag;
    }

    public void setConnectMessage(String connectMessage) {
        this.connectMessage = connectMessage;
    }

    public String getConnectMessage() {
        return connectMessage;
    }
}
