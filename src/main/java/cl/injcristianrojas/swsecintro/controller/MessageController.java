package cl.injcristianrojas.swsecintro.controller;

import cl.injcristianrojas.swsecintro.model.Message;
import cl.injcristianrojas.swsecintro.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages/all")
    public List<Message> getAllMessages() {
        return messageService.findAllMessages();
    }

    @PostMapping("/messages/new")
    public Map<String,Object> postNewMessages(@RequestBody Message newMessage){
        return messageService.save(newMessage);
    }

}
