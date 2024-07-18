package servlet;
/*
 A WebSocket end-point to handle real-time messaging
 */

/*
A WebSocket is a communication protocol that provides full-duplex communication channels over a single, long-lived connection between a client and a server. Unlike HTTP, which follows a request-response model where the client requests data and the server responds, WebSockets enable real-time, bidirectional communication. This is particularly useful for applications that require frequent updates from the server, such as chat applications, live notifications, online gaming, and real-time data feeds.

### Key Features of WebSocket

1. Full-Duplex Communication: WebSockets allow both the client and the server to send and receive messages simultaneously. This is in contrast to HTTP, where communication is half-duplex (request/response).

2. Persistent Connection: Once established, a WebSocket connection remains open, allowing for continuous data exchange without the overhead of repeatedly establishing new connections, as with HTTP requests.

3. Low Latency: The persistent connection and full-duplex nature reduce the latency associated with opening and closing connections, making WebSockets suitable for real-time applications.

4. Lightweight Protocol: WebSockets use a small amount of overhead compared to HTTP, making them more efficient for transmitting small messages frequently.

### How WebSocket Works

1. Handshake: The communication begins with an HTTP handshake to establish the WebSocket connection. The client sends a special HTTP request to the server indicating that it wants to open a WebSocket connection.

2. Connection Upgrade: If the server supports WebSockets, it responds with an agreement to upgrade the connection from HTTP to WebSocket. This upgrade process involves exchanging specific headers, such as `Upgrade` and `Connection`.

3. Data Frames: Once the connection is established, data is exchanged in frames. WebSocket frames can carry text data, binary data, or control information (e.g., closing the connection).

4. Closing the Connection: Either the client or the server can close the WebSocket connection. This is done by sending a control frame indicating that the connection should be closed.

### Use Cases

- Chat Applications: Real-time messaging applications where users need to send and receive messages instantly.
- Live Sports Updates: Providing real-time updates of scores and events in sports.
- Online Gaming: Enabling real-time interaction between players in online multiplayer games.
- Financial Tickers: Streaming real-time stock prices and market data.
- Collaborative Editing: Applications like Google Docs where multiple users edit documents simultaneously.
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

/*

This Java class, `ChatServerEndpoint`, is a WebSocket endpoint designed to handle real-time messaging in a chat server application. 
https://jakarta.ee/specifications/websocket/2.2/apidocs/server/

### Imports and Their Usage

1. `jakarta.websocket.OnClose`: Annotation for methods that handle the event when a WebSocket connection is closed.
2. `jakarta.websocket.OnMessage`: Annotation for methods that handle incoming messages.
3. `jakarta.websocket.OnOpen`: Annotation for methods that handle the event when a WebSocket connection is opened.
4. `jakarta.websocket.Session`: Represents a WebSocket session, allowing interaction with the client.
5. `jakarta.websocket.server.ServerEndpoint`: Annotation that declares a WebSocket endpoint.
6. `jakarta.servlet.http.HttpSession`: Represents an HTTP session, allowing for session tracking and management.
7. `jakarta.websocket.HandshakeResponse`: Represents the server's response to a WebSocket handshake request.
8. `jakarta.websocket.server.HandshakeRequest`: Represents the client's WebSocket handshake request.
9. `jakarta.websocket.server.ServerEndpointConfig`: Provides configuration for WebSocket endpoints.

### Class and Methods Explanation

1. `@ServerEndpoint(value = "/chatServer", configurator = ChatServerEndpoint.Configurator.class)`
   - Declares the class as a WebSocket endpoint at the URI `/chatServer`.
   - Uses a custom configurator class (`Configurator`) to modify the handshake.

2. Static Field: `clients`
   - A synchronized set to store active WebSocket sessions (`Session` objects).

3. `@OnOpen public void onOpen(Session session)`
   - Method called when a new WebSocket connection is established.
   - Adds the new session to the `clients` set.

4. `@OnClose public void onClose(Session session)`
   - Method called when a WebSocket connection is closed.
   - Removes the session from the `clients` set.

5. `@OnMessage public void onMessage(String message, Session session) throws IOException`
   - Method called when a message is received from a client.
   - Retrieves the username from the session's user properties.
   - Sends the message to all connected clients, prefixed with the username.
   - Uses synchronized block to ensure thread-safe iteration over the `clients` set.

6. `public static class Configurator extends ServerEndpointConfig.Configurator`
   - A nested static class to configure the WebSocket endpoint.
   - Overrides `modifyHandshake` method to transfer the username from the HTTP session to the WebSocket session.
   - Retrieves the `HttpSession` from the `HandshakeRequest`.
   - Puts the username attribute into the `ServerEndpointConfig`'s user properties.

### Detailed Functionality

- WebSocket Lifecycle Events: 
  - The `onOpen` method is called when a new client connects, adding the session to the `clients` set.
  - The `onClose` method is called when a client disconnects, removing the session from the `clients` set.
  
- Message Handling: 
  - The `onMessage` method receives messages from clients, retrieves the sender's username, and broadcasts the message to all connected clients.
  
- Handshake Configuration: 
  - The `Configurator` class handles the handshake process, extracting the username from the HTTP session and storing it in the WebSocket session's user properties.

This setup ensures that each client can send and receive messages in real time, and messages are broadcasted to all connected clients, identified by their usernames.

*/