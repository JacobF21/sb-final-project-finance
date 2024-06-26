package demo.com.db_final_project_frontend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import demo.com.db_final_project_frontend.infra.WebSocketSessions;

@Service
public class MsgTemplate {
   public static final String BROADCAST_DESTINATION = "/topic/messages";
    private static final String USER_TOPIC = "/subscribe";

    @Autowired
    private WebSocketSessions sessions;

    @Autowired
    private SimpMessagingTemplate template;

    public void sendMsgTo(String destination, Object msg) {
        template.convertAndSend(destination, msg);
    }

    public void sendMsgToSession(String sessionId, Object msg) {
        template.convertAndSendToUser(sessionId, USER_TOPIC, msg);
    }

    public void sendMsgToUser(String user, Object msg) {
        sessions.getSessionIds(user).forEach(sessionId -> {
            template.convertAndSendToUser(sessionId, USER_TOPIC, msg);
        });
    }

    public void broadcast(Object msg) {
        sendMsgTo(BROADCAST_DESTINATION, msg);
    }

}
