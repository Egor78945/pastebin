package org.pastebin.application.user_server.controller;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.user_server.annotation.MessageControllerExceptionHandler;
import org.pastebin.application.user_server.exception.MessageFormatException;
import org.pastebin.application.user_server.exception.RequestCancelledException;
import org.pastebin.application.user_server.model.MessageRequestBody;
import org.pastebin.application.user_server.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
@MessageControllerExceptionHandler
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<String> postMessage(@RequestBody MessageRequestBody requestBody) throws MessageFormatException {
        Long messageId = messageService.postMessage(requestBody.getMessage());
        return ResponseEntity.ok(String.format("Your personal link to published message: %s", messageService.buildPersonalReference(messageId)));
    }

    @GetMapping
    public ResponseEntity<String> getMessage(@RequestParam("id") Long id) throws RequestCancelledException {
        return ResponseEntity.ok(messageService.getMessage(id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMessage(@RequestParam("id") Long id) throws RequestCancelledException {
        return ResponseEntity.ok(String.format("Message with id %s has been deleted.", messageService.deleteMessage(id)));
    }
}