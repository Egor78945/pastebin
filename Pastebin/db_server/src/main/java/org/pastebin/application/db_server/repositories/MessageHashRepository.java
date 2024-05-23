package org.pastebin.application.db_server.repositories;

import org.pastebin.application.db_server.models.entities.MessageHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageHashRepository extends JpaRepository<MessageHash, Long> {
    void removeMessageHashById(Long id);
}
