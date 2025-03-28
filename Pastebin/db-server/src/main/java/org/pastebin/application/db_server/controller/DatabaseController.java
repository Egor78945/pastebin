package org.pastebin.application.db_server.controller;


import lombok.RequiredArgsConstructor;
import org.pastebin.application.db_server.annotation.DatabaseControllerExceptionHandler;
import org.pastebin.application.db_server.exception.RequestCancelledException;
import org.pastebin.application.db_server.model.entity.MessageHash;
import org.pastebin.application.db_server.service.MessageHashService;
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
    public Long postMessageHash(@RequestParam("hash") Integer messageHash) {
        return messageHashService.save(new MessageHash(messageHash));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Integer getMessageHashById(@RequestParam("id") Long id) throws RequestCancelledException {
        return messageHashService.getById(id).getHash();
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public Long deleteMessageHash(@RequestParam("id") Long id) {
        messageHashService.deleteById(id);
        return id;
    }
}
