package com.ch.ch11;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
//채팅하기 위해 접속한 클라이언트의 session(연결정보)을 저장
public class ChatSocketHandler extends TextWebSocketHandler{
	private Map<String, WebSocketSession> users = new HashMap<String, WebSocketSession>();
	//연결된 후에 클라이언트 정보를 map에 저장
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		users.put(session.getId(), session);
	}
	// 연결이 해제됐을 때 map에서 제거
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		users.remove(session.getId());
	}
	// 클라이언트에서 메세지가 왔을 때 모든 클라이언트에 메세지 전송
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		Collection<WebSocketSession> sessions = users.values();
		for(WebSocketSession ws : sessions) {
			ws.sendMessage(message);
		}
	}
}
