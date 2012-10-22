package com.cosmocoder.longpollchat.domain;

import com.cosmocoder.longpollchat.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long>{
}
