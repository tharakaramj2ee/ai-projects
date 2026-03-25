package com.demo.ai.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class ChatModelAIService {

	private final OpenAiChatModel openAiChatModel;
	
	public ChatModelAIService(OpenAiChatModel openAiChatModel) {
		this.openAiChatModel = openAiChatModel;
	}
	
	public String generateTextResponse(String userPrompt) {
		
		Prompt prompt = new Prompt(userPrompt,
								OpenAiChatOptions.builder()
												 .model("gpt-4o")
												 .maxTokens(150)
												 .temperature(0.07)
												 .build());
		
		ChatResponse chatResponse = openAiChatModel.call(prompt);
		
		return chatResponse.getResult().getOutput().getText();
	}
}
