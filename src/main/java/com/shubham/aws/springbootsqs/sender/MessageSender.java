package com.shubham.aws.springbootsqs.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSender {

    @Value("${cloud.aws.end-point.url}")
    private String endpoint;
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @GetMapping("/send/{message}")
    public void send(@PathVariable(value="message") String message){
        Message payload = MessageBuilder.withPayload(message)
                .setHeader("Sender","Shubham Kumar")
                .build();

        queueMessagingTemplate.send(endpoint,payload);
    }
}
