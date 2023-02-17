package site.lemongproject.web.challenge.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.socket.WebSocketSession;
import site.lemongproject.common.config.ChatConfigurator;
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
@ServerEndpoint(value = "/socket/chatt/{chatRoomNo}" , configurator = ChatConfigurator.class)
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
        this.cSession = (HttpSession)config.getUserProperties().get("cSession"); // 넣어놨던 HTTP Session을 꺼낸다.
//      clients.add(session);
        cSession.setAttribute("chatRoomNo",chatRoomNo);
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
//        System.out.println("receive message : {}" + message);

        JsonParser parse = new JsonParser();
        Object obj = parse.parse(message);

        JsonObject jsonObj = (JsonObject)obj;

        // 이름(보낸사람)
        int name = Integer.parseInt(jsonObj.get("userNo").getAsString());
        System.out.println(name + " =======> [success]");

        // 챌린지 번호
        int challengeNo = Integer.parseInt(jsonObj.get("chatRoomNo").getAsString());
        System.out.println(challengeNo + " =======> [success]");

        // 보낸 메세지
        String msg = jsonObj.get("msg").getAsString();
        System.out.println(msg + " =======> [success]");

        ChallengeChat c = new ChallengeChat();

        c.setChallengeNo(challengeNo);
        System.out.println("challengeNo : " + c.getChallengeNo());

        c.setChatMessage(msg);
        System.out.println("chatMsg : " + c.getChatMessage());

        c.setUserNo(name);
        System.out.println("userNo : " + c.getUserNo());

        System.out.println(cSession.getAttribute("chatRoomNo") + " : success !7!6!5 "); // 세션 안의 키를 통해 값을 꺼낸다.

        int chatRoomsNo = (Integer) cSession.getAttribute("chatRoomNo");

        for (Session s : clients) {
            if (chatRoomsNo == (int) s.getUserProperties().get("chatRoomNo")) {
                System.out.println("send data : {}" + message); // 리액트에서 넘어오는 채팅 데이터

            if (!s.equals(session)) {

                }
                challengeService.insertChatData(c);
                    s.getBasicRemote().sendText(message);
            }
        }

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("session close : {}" + session);
//        cSession.removeAttribute("chatRoomNo");
        clients.remove(session);
    }

}