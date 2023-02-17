package site.lemongproject.web.challenge.controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import site.lemongproject.web.challenge.model.dto.ChallengeChat;
import site.lemongproject.web.challenge.service.ChallengeService;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Service
@ServerEndpoint("/socket/chatt/{chatRoomNo}")
//@RequiredArgsConstructor // 이거 있으면 오류뜨기 때문에 직접 this. ... 해서 생성자 만들어 줄 것.
public class ChallengeChatSocketController {

    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
    private static ChallengeService challengeService;
    //
    @Inject
    public void setChallengeService(ChallengeService challengeService){
        ChallengeChatSocketController.challengeService = challengeService;
    }

    @OnOpen
    public void onOpen(Session session) {

        System.out.println("open session : {}, clients={}" + session.toString() + clients);

        if(!clients.contains(session)) {
            clients.add(session);
            System.out.println("session open : {}" + session);
        }else{
            System.out.println("이미 연결된 session");
        }
    }

    @OnMessage
    public void onMessage(String message, Session session , @PathParam("chatRoomNo")int chatRoomNo) throws IOException {
//        System.out.println("receive message : {}" + message);

        System.out.println(chatRoomNo + " : success!!!");

        JsonParser parse = new JsonParser();
        Object obj = parse.parse(message);

        JsonObject jsonObj = (JsonObject)obj;

        // 이름(보낸사람)
        int name = Integer.parseInt(jsonObj.get("userNos").getAsString());
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



        for (Session s : clients) {
            System.out.println("send data : {}" + message); // 리액트에서 넘어오는 채팅 데이터

            if(chatRoomNo == challengeNo){
                if(!s.equals(session)) {
                    challengeService.insertChatData(c);
                    s.getBasicRemote().sendText(message);
                }
            }

        }
    }
    @OnClose
    public void onClose(Session session) {
        System.out.println("session close : {}" + session);
        clients.remove(session);
    }
}