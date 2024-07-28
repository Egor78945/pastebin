package org.pastebin.application.message_db_server.controller;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.message_db_server.annotation.MessageControllerExceptionHandler;
import org.pastebin.application.message_db_server.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/minio")
@MessageControllerExceptionHandler
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<String> get(@RequestParam("hash") String hash) throws Exception {
        return ResponseEntity.ok(messageService.get(hash));
    }
}
