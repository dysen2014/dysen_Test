package com.dysen.test.mina;

import org.apache.mina.core.session.IoSession;

/**
 * Created by dy on 2016-12-05.
 */

public class SessionManager {

    private static SessionManager sessionManager = null;
    /**
     * 最终与服务器进行通信的对象
     */
    private IoSession ioSession;

    public static SessionManager getInstance(){

        if (sessionManager == null){
            synchronized (SessionManager.class){
                if (sessionManager == null){
                    sessionManager = new SessionManager();
                }
            }
        }
        return sessionManager;
    }

    private SessionManager(){}

    public void setSession(IoSession ioSession){
        this.ioSession = ioSession;
    }

    public void write2Server(Object msg){

        if (ioSession != null){
            ioSession.write(msg);
        }
    }

    public void closeSession(){
        if (ioSession != null){
            ioSession.closeOnFlush();
        }
    }

    public void removeSession(){
        this.ioSession = null;
    }
}
