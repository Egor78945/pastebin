package org.pastebin.application.db_server.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "message_hashes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageHash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "hash")
    private String hash;

    public MessageHash(String hash) {
        this.hash = hash;
    }
}
