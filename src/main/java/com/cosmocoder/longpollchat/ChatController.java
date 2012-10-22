package com.cosmocoder.longpollchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/chat/*", produces = "text/plain;charset=UTF-8")
public class ChatController {

    @Autowired
    public ChatController(ChatApp chatApp) {
        this.chatApp = chatApp;
    }

    private ChatApp chatApp;

    @RequestMapping(value = "joinRoom", method = RequestMethod.GET)
    public @ResponseBody String joinRoom(@RequestParam String userName, HttpSession session) {
        return chatApp.joinRoom(session.getId(), userName);
    }

    @RequestMapping(value = "listenToStatusMessages", method = RequestMethod.GET)
    public @ResponseBody DeferredResult<String> listenToStatusMessages(HttpSession session) {
        SpringMvcDeferredResultListener<String> listener = new SpringMvcDeferredResultListener<String>();
        chatApp.listenToStatusMessages(session.getId(), listener);
        return listener.getDeferredResult();
    }

    @RequestMapping(value = "listenToChatMessages", method = RequestMethod.GET)
    public @ResponseBody DeferredResult<String> listenToChatMessages(HttpSession session) {
        SpringMvcDeferredResultListener<String> listener = new SpringMvcDeferredResultListener<String>();
        chatApp.listenToChatMessages(session.getId(), listener);
        return listener.getDeferredResult();
    }

    @RequestMapping(value = "sendChatMessage", method = RequestMethod.GET)
    public @ResponseBody String sendChatMessage(@RequestParam String message, HttpSession session) {
        return chatApp.sendChatMessage(session.getId(), message);
    }

}