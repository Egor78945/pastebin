package org.pastebin.application.db_server.controllers;


import lombok.RequiredArgsConstructor;
import org.pastebin.application.db_server.annotations.DatabaseControllerExceptionHandler;
import org.pastebin.application.db_server.exceptions.RequestCancelledException;
import org.pastebin.application.db_server.models.entities.MessageHash;
import org.pastebin.application.db_server.services.MessageHashService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/database")
@RequiredArgsConstructor
@DatabaseControllerExceptionHandler
public class DatabaseController {
    private final MessageHashService messageHashService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public String postMessageHash(@RequestParam("hash") String messageHash) {
        messageHashService.save(new MessageHash(messageHash));
        return messageHash;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getMessageHashById(@RequestParam("id") Long id) throws RequestCancelledException {
        return messageHashService.getById(id).getHash();
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMessageHash(@RequestParam("id") Long id) {
        messageHashService.deleteById(id);
        return id;
    }
}
