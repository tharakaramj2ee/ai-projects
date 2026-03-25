package com.demo.ai.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageModelAIService {

	private OpenAiImageModel openAiImageModel;
	
	public ImageModelAIService(OpenAiImageModel openAiImageModel) {
		this.openAiImageModel = openAiImageModel;
	}
	
	public String generateAiImages(String userPrompt) {
		
		OpenAiImageOptions imageOptions = OpenAiImageOptions.builder()
												.model("dall-e-3") // optional, default is dall-e-3
												.height(1024)
												.width(1024)
												.style("vivid") //vivid/natural
												.N(1) // No of Image samples wanted from Model, current model default is 1
												.build();
		
		
		ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt(userPrompt, imageOptions));
		
		return imageResponse.getResult().getOutput().getUrl();
	}
}
