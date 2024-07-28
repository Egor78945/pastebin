package org.pastebin.application.db_server.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.pastebin.application.db_server.exception.RequestCancelledException;
import org.pastebin.application.db_server.model.entity.MessageHash;
import org.pastebin.application.db_server.repository.MessageHashRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageHashService {
    private final MessageHashRepository messageHashRepository;

    @Transactional
    public Long save(MessageHash messageHash) {
        return messageHashRepository.save(messageHash).getId();
    }

    @Transactional
    public MessageHash getById(Long id) throws RequestCancelledException {
        return messageHashRepository
                .findById(id)
                .orElseThrow(() ->
                        new RequestCancelledException(String.format("Message hash with id %s is not found.", id)));
    }

    @Transactional
    public void deleteById(Long id) {
        messageHashRepository.removeMessageHashById(id);
    }
}
