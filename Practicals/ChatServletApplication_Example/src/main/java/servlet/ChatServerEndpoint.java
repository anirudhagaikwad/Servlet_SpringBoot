package servlet;
/*
 A WebSocket end-point to handle real-time messaging
 */

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint(value = "/chatServer", configurator = ChatServerEndpoint.Configurator.class)
public class ChatServerEndpoint {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        Map<String, Object> userProperties = session.getUserProperties();
        String username = (String) userProperties.get("username");

        synchronized (clients) {
            for (Session client : clients) {
                if (client.isOpen()) {
                    client.getBasicRemote().sendText(username + ": " + message);
                }
            }
        }
    }

    public static class Configurator extends ServerEndpointConfig.Configurator {
        @Override
        public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
            HttpSession httpSession = (HttpSession) request.getHttpSession();
            if (httpSession != null) {
                sec.getUserProperties().put("username", httpSession.getAttribute("username"));
            }
        }
    }
}
