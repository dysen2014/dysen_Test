package com.dysen.test.mina;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;

/**
 * Created by dy on 2016-12-05.
 * 完成封装 connection disconnection 方法供外层调用
 */

public class ConnectManager {

    public static final String BROADCAST_ACTTION = "com.dysen.test";
    public static final String MESSAGE = "message";

    private ConnectionConfig connectionConfig;
    private WeakReference<Context> weakReference;
    private NioSocketConnector nioSocketConnector;
    private IoSession ioSession;
    private InetSocketAddress inetSocketAddress;

    public ConnectManager(ConnectionConfig config){
        this.connectionConfig = config;
        this.weakReference = new WeakReference<Context>(config.getContext());

        init();
    }

    private void init() {
        inetSocketAddress = new InetSocketAddress(connectionConfig.getIp(), connectionConfig.getPort());
        nioSocketConnector = new NioSocketConnector();
        nioSocketConnector.getSessionConfig().setReadBufferSize(connectionConfig.getReadBufferSize());
        nioSocketConnector.getFilterChain().addLast("logging", new LoggingFilter());
        nioSocketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
        nioSocketConnector.setHandler(new DefaultHandler(context));
    }

    public boolean connect(){

        try {
            ConnectFuture connectFuture = nioSocketConnector.connect();
            connectFuture.awaitUninterruptibly();
            ioSession = connectFuture.getSession();
        }catch (Exception e){
            return false;
        }

        return ioSession == null ? false : true;
    }

    public void disConnect(){

        nioSocketConnector.dispose();
        nioSocketConnector = null;
        ioSession = null;
        inetSocketAddress = null;
        weakReference = null;
    }

    private Context context;
    /**
     * 负责我们 session 对象的创建监听及消息发送的监听
     */
    private static class DefaultHandler extends IoHandlerAdapter {

        private Context context;

        public DefaultHandler(Context context){
            this.context = context;
        }
        @Override
        public void sessionCreated(IoSession session) throws Exception {
            super.sessionCreated(session);
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            //将我们的 session保存到我们的 sessionmanager类中，从而科一发送消息到服务器
        }

        /**
         * 消息的接收处理
         * @param session
         * @param message
         * @throws Exception
         */
        @Override
        public void messageReceived(IoSession session, Object message) throws Exception {

            if (context != null){
                Intent intent = new Intent(BROADCAST_ACTTION);
                intent.putExtra(MESSAGE, message.toString());
                //发送局部广播 (其它应用监听不到的，更安全)
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        }

        /**
         * 消息的发送处理
         * @param session
         * @param message
         * @throws Exception
         */
        @Override
        public void messageSent(IoSession session, Object message) throws Exception {
            super.messageSent(session, message);
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            super.sessionClosed(session);
        }
    }
}
