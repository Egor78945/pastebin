package org.pastebin.application.api_server.controllers;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.api_server.annotations.MessageControllerExceptionHandler;
import org.pastebin.application.api_server.exceptions.MessageFormatException;
import org.pastebin.application.api_server.models.MessageRequestBody;
import org.pastebin.application.api_server.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@MessageControllerExceptionHandler
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/post")
    public ResponseEntity<String> postMessage(@RequestBody MessageRequestBody requestBody) throws MessageFormatException {
        messageService.sendMessage(requestBody.getMessage());
        return ResponseEntity.ok("Message has been posted.");
    }
}