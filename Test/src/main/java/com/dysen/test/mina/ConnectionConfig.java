package com.dysen.test.mina;

import android.content.Context;

/**
 * Created by dy on 2016-12-05.
 * 一个构建者模式
 */

class ConnectionConfig {

    private Context context;
    private String ip;
    private int port;
    private int readBufferSize;
    private long connetionTime;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public void setReadBufferSize(int readBufferSize) {
        this.readBufferSize = readBufferSize;
    }

    public long getConnetionTime() {
        return connetionTime;
    }

    public void setConnetionTime(long connetionTime) {
        this.connetionTime = connetionTime;
    }


    /**
     * 构建模式
     */
    public static class Builder{
        private Context context;
        private String ip = "192.168.0.12";
        private int port = 9001;
        private int readBufferSize = 10240;
        private long connetionTime = 10000;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setIp(String ip){
            this.ip = ip;
            return this;
        }
        public Builder setPort(int port) {
            this.port = port;
            return this;
        }
        public Builder setReadBufferSize(int readBufferSize) {
            this.readBufferSize = readBufferSize;
            return this;
        }
        public Builder setConnetionTime(long connetionTime) {
            this.connetionTime = connetionTime;
            return this;
        }

        private void applyConfig(ConnectionConfig connectionConfig){
            connectionConfig.context = this.context;
            connectionConfig.ip = this.ip;
            connectionConfig.port = this.port;
            connectionConfig.readBufferSize = this.readBufferSize;
            connectionConfig.connetionTime = this.connetionTime;
        }

        public ConnectionConfig Builder(){
             ConnectionConfig connectionConfig = new ConnectionConfig();
            applyConfig(connectionConfig);

            return connectionConfig;
        }
    }
}
