package com.demo.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ai.service.AudioModelAIService;
import com.demo.ai.service.ChatModelAIService;
import com.demo.ai.service.ImageModelAIService;

@RestController
@RequestMapping("/ai-api/v1")
public class AIIntegrationController {
	

	private ChatModelAIService chatModelAIService;
	
	private ImageModelAIService imageModelAIService;
	
	private AudioModelAIService audioModelAIService;
	
	
	public AIIntegrationController(ChatModelAIService chatModelAIService,
									 ImageModelAIService imageModelAIService,
									 AudioModelAIService audioModelAIService) {
			this.chatModelAIService = chatModelAIService;
			this.imageModelAIService = imageModelAIService;
			this.audioModelAIService = audioModelAIService;
	}
	
	
	@GetMapping("/open-chat/{prompt}")
	public String chatWithOpenAi(@PathVariable String prompt) {
	
		System.out.println("START----AI INtegration Controller-----Chat with Open AI model........");
		
		return chatModelAIService.generateTextResponse(prompt);
	}
	
	
	@GetMapping("/gen-image/{prompt}")
	public String generateImageWithAI(@PathVariable String prompt) {
		
		System.out.println("START----AI INtegration Controller-----generateImageWithAI with Open AI model........");
		
		return imageModelAIService.generateAiImages(prompt);
	}
	
	@GetMapping("/gen-audio/{prompt}")
	public String generateAudioWithAI(@PathVariable String prompt){
		
		System.out.println("START----AI INtegration Controller-----generateAudioWithAI with Open AI model........");
		
		return audioModelAIService.generateAudioData(prompt);
	}
}
