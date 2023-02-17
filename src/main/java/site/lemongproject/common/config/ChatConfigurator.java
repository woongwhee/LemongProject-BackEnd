package site.lemongproject.common.config;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;
public class ChatConfigurator extends Configurator{

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) { // 통신 규칙 정하기

        HttpSession session = (HttpSession)request.getHttpSession(); // 1) 세션 정보를 꺼내라

        sec.getUserProperties().put("cSession", session); // 2) 엔드포인트 전달하고 싶은 사용자 정보를 담음 ServerEndpointConfig는 엔드포인트에 접근 가능하다.

    }
}