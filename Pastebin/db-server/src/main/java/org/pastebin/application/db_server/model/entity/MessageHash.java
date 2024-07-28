package org.pastebin.application.db_server.model.entity;

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
    private Integer hash;

    public MessageHash(Integer hash) {
        this.hash = hash;
    }
}
