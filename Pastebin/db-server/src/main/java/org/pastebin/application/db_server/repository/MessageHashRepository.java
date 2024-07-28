package org.pastebin.application.db_server.repository;

import org.pastebin.application.db_server.model.entity.MessageHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageHashRepository extends JpaRepository<MessageHash, Long> {
    void removeMessageHashById(Long id);
    MessageHash findMessageHashById(Long id);
}
