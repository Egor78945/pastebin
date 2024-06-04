package org.pastebin.application.message_db_server.controllers;

import lombok.RequiredArgsConstructor;
import org.pastebin.application.message_db_server.annotations.MessageControllerExceptionHandler;
import org.pastebin.application.message_db_server.services.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/minio")
@MessageControllerExceptionHandler
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<String> save(@RequestParam("hash") String hash, @RequestParam("message") String message) throws Exception {
        return ResponseEntity.ok(messageService.save(hash, message));
    }

    @GetMapping
    public ResponseEntity<String> get(@RequestParam("hash") String hash) throws Exception {
        return ResponseEntity.ok(messageService.get(hash));
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam("hash") String hash) throws Exception {
        return ResponseEntity.ok(messageService.delete(hash));
    }
}
