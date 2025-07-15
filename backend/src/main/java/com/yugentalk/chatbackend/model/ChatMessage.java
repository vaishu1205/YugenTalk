package com.yugentalk.chatbackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    @Id
    private String id;

    private String userId;      // (e.g., email or name)
    private String message;
    private String sender;      // "user" or "bot"
    private LocalDateTime timestamp;
}
