package com.yaping.common.support.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2017/10/24.
 */
@Controller
public class WsController {


    @MessageMapping("welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        System.out.println(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }


    public class RequestMessage {

        private String name;

        public String getName() {
            return name;
        }
    }

    public class ResponseMessage {
        private String responseMessage;

        public ResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        public String getResponseMessage() {
            return responseMessage;
        }
    }

}
