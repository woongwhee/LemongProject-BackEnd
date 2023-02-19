package site.lemongproject.web.challenge.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.socket.WebSocketSession;
//import site.lemongproject.common.config.ChatConfigurator;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;
import site.lemongproject.web.challenge.service.ChallengeService;
import site.lemongproject.web.member.model.vo.Profile;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//@Component
@Service
@ServerEndpoint(value = "/socket/chatt/{chatRoomNo}" )
//@ServerEndpoint(value = "/socket/chatt/{chatRoomNo}" , configurator = ChatConfigurator.class)
//@RequiredArgsConstructor // 이거 있으면 오류뜨기 때문에 직접 this. ... 해서 생성자 만들어 줄 것.
public class ChallengeChatSocketController {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    private HttpSession cSession;

    private static ChallengeService challengeService;
    //
    @Inject
    public void setChallengeService(ChallengeService challengeService){
        ChallengeChatSocketController.challengeService = challengeService;
    }

    @OnOpen
    public void onOpen(Session session , EndpointConfig config) {
        int chatRoomNo= Integer.parseInt(session.getPathParameters().get("chatRoomNo"));
        System.out.println(chatRoomNo);
        if (clients.contains(session)) {
            System.out.println("[LEMONG] 이미 연결된 세션입니다. > " + session);
        } else {
            clients.add(session);
            System.out.println("[LEMONG] 새로운 세션입니다. > " + session);
        }
        session.getUserProperties().put("chatRoomNo",chatRoomNo);
        System.out.println("open session : {}, clients={}" + session.toString() + clients);
        if(!clients.contains(session)) {
            clients.add(session);
            System.out.println("session open : {}" + session);
        }else{
            System.out.println("이미 연결된 session");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session ) throws IOException {
        Gson gson=new Gson();
        ChallengeChat challengeChat = gson.fromJson(message, ChallengeChat.class);
        System.out.println(challengeChat+"팻이에요");
        int chatRoomsNo = (Integer) cSession.getAttribute("chatRoomNo");
        challengeService.insertChatData(challengeChat);
        for (Session s : clients) {
            if (chatRoomsNo == (int) s.getUserProperties().get("chatRoomNo")) {
                System.out.println("send data : {}" + message); // 리액트에서 넘어오는 채팅 데이터
            s.getBasicRemote().sendText(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("session close : {}" + session);
        cSession.removeAttribute("chatRoomNo");
        clients.remove(session);
    }

}