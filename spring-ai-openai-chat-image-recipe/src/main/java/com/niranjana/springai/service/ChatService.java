package com.niranjana.springai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

import com.niranjana.springai.exception.AiServiceException;

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);
    private final ChatModel chatModel;

    public ChatService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String getResponse(String prompt){
        log.info("Received chat request: {}", prompt);
        try {
            String response = chatModel.call(prompt);
            log.info("Chat response generated successfully");
            return response;
        } catch (Exception e) {
            log.error("Error while generating chat response", e);
            throw new AiServiceException("Failed to generate chat response");
        }
    }

    public String getResponseOptions(String prompt){
        log.info("Received chat request with options: {}", prompt);
        try {
            ChatResponse response = chatModel.call(
                    new Prompt(
                            prompt,
                            OpenAiChatOptions.builder()
                                    .model("gpt-4o")
                                    .temperature((double) 0.4F)
                                    .build()
                    ));
            return response.getResult().getOutput().getText();
        } catch (Exception e) {
            log.error("Error in chat with options", e);
            throw new AiServiceException("Failed to generate chat response with options");
        }
    }
}