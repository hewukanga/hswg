package com.jiumai.base.sm.config;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;


@ServerEndpoint(value = "/webSocket/{opId}")
@Component
public class NoticeWebSocket {
	
	private static SysLog log = SysLogFactory.getLogger(NoticeWebSocket.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
    //private static CopyOnWriteArraySet<NoticeWebSocket> webSocketSet = new CopyOnWriteArraySet<NoticeWebSocket>();
    private static ConcurrentHashMap<Long,NoticeWebSocket> webSocketSet = new ConcurrentHashMap<Long,NoticeWebSocket>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    //当前登录对象的ID
    private Long opId;
 
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("opId") String opId,Session session) {
        this.session = session;
        this.opId= Long.parseLong(opId);
        // webSocketSet.add(this);     //加入set中
        webSocketSet.put(this.opId, this);
        addOnlineCount();           //在线数加1
        log.info("有新连接加入！当前在线人数为" + getOnlineCount());
//        try {
//        	 sendMessage("连接成功");
//        } catch (IOException e) {
//            log.error("websocket IO异常");
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // webSocketSet.remove(this);  //从set中删除
    	webSocketSet.remove(this.opId);
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
 
    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
    	
    	log.info("来自客户端的消息:" + message);
        //群发消息
        for (NoticeWebSocket item : webSocketSet.values()) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 
	/**
	 * 
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }
 
 
    public  void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
    	NoticeWebSocket.onlineCount++;
    }
 
    public static synchronized void subOnlineCount() {
    	NoticeWebSocket.onlineCount--;
    }
}
