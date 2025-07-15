package com.yugentalk.chatbackend.controller;

import com.yugentalk.chatbackend.model.ChatMessage;
import com.yugentalk.chatbackend.repository.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "*")  // Allow frontend to connect
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageRepository chatRepo;

    // 🟢 POST: Save message
    @PostMapping("/save")
    public ChatMessage saveMessage(@RequestBody ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        return chatRepo.save(message);
    }

    // 🟢 GET: Get messages by user
    @GetMapping("/{userId}")
    public List<ChatMessage> getMessages(@PathVariable String userId) {
        return chatRepo.findByUserIdOrderByTimestampAsc(userId);
    }
}
