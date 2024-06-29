package demo.com.db_final_project_frontend.controller.Impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import demo.com.db_final_project_frontend.infra.WebSocketSessions;
import demo.com.db_final_project_frontend.model.Message;
import demo.com.db_final_project_frontend.model.OutputMessage;
import demo.com.db_final_project_frontend.service.MsgTemplate;

@Controller
public class ChatController {
    @Autowired
    private WebSocketSessions sessions;

    @MessageMapping("/chat")
    @SendTo(MsgTemplate.BROADCAST_DESTINATION)
    public OutputMessage send(final Message message) throws Exception {
        final String time = new Date().toString();
        return new OutputMessage(time, message);
    }
}
