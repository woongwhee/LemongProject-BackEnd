package site.lemongproject.web.challenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;
import site.lemongproject.web.challenge.service.ChallengeService;
import site.lemongproject.web.member.model.vo.Profile;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class ChallengeChatHandler extends TextWebSocketHandler {
    private ChallengeService challengeService;
    @Autowired
    public void setChallengeService(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }
    private Map<Integer, ArrayList<WebSocketSession>> RoomList = new ConcurrentHashMap<Integer, ArrayList<WebSocketSession>>();
    private Set<WebSocketSession> clients=Collections.synchronizedSet(new HashSet<>());

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Integer challengeNo=(Integer) session.getAttributes().get("challengeNo");
        if(challengeNo==null){
            session.close(); return;
        }
        Integer userNo=((Profile)session.getAttributes().get("loginUser")).getUserNo();
        for (WebSocketSession client : clients) {
            if(((Profile)client.getAttributes().get("loginUser")).getUserNo()==userNo){
                clients.remove(client);
            }
        }
        for (ArrayList<WebSocketSession> value : RoomList.values()) {
            for (WebSocketSession webSocketSession : value) {
                if(((Profile)webSocketSession.getAttributes().get("loginUser")).getUserNo()==userNo){
                    value.remove(webSocketSession);
                    webSocketSession.close();
                    //한사람당 한세션만 유지
                };
            }
        }
        clients.add(session);
        ArrayList<WebSocketSession> Room = RoomList.get(challengeNo);
        if(Room==null){
            Room=new ArrayList<>();
            RoomList.put(challengeNo,Room);
        }
        Room.add(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Map<String, Object> sessionMap = session.getAttributes();
        Integer challengeNo=(Integer) sessionMap.get("challengeNo");
        Integer userNo=((Profile)sessionMap.get("loginUser")).getUserNo();
        ChallengeChat challengeChat = new ChallengeChat(challengeNo,userNo,message.getPayload());
        ChallengeChat result=challengeService.insertChatData(challengeChat);
        System.out.println(result);
        if(result!=null){
            ArrayList<WebSocketSession> sessions = RoomList.get(challengeNo);
            System.out.println(sessions);
            ObjectMapper objectMapper=new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            String chatString = objectMapper.writeValueAsString(result);
            TextMessage textMessage=new TextMessage(chatString);
            for (WebSocketSession websession : sessions) {
                websession.sendMessage(textMessage);
            }
        }
//        super.handleMessage(session, message);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Integer challengeNo =(Integer) session.getAttributes().get("challengeNo");
        RoomList.get(challengeNo).remove(session);
        clients.remove(session);
        System.out.println("sessionclose");
        super.afterConnectionClosed(session, status);
    }
}