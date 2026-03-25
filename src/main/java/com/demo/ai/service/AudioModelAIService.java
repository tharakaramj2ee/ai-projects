package com.demo.ai.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.ai.audio.tts.TextToSpeechPrompt;
import org.springframework.ai.audio.tts.TextToSpeechResponse;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;

@Service
public class AudioModelAIService {

	private OpenAiAudioSpeechModel openAiAudioSpeechModel;
	
	public AudioModelAIService(OpenAiAudioSpeechModel openAiAudioSpeechModel) {
		this.openAiAudioSpeechModel = openAiAudioSpeechModel;
	}
	
	/*@Retryable(
		    retryFor = {
		        org.springframework.web.client.HttpServerErrorException.class,
		        org.springframework.web.client.ResourceAccessException.class
		    },
		    maxAttempts = 4,
		    backoff = @Backoff(delay = 3000, multiplier = 2)
		)*/
	public String generateAudioData(String userPrompt) {
		
		OpenAiAudioSpeechOptions openAiAudioSpeechOptions = OpenAiAudioSpeechOptions.builder()
																			.model("gpt-4o-mini-tts")
																			.voice(OpenAiAudioApi.SpeechRequest.Voice.CORAL)
																			.responseFormat(OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3)
																			.build();
		
		TextToSpeechResponse textToSpeechResponse = openAiAudioSpeechModel.call(new TextToSpeechPrompt(userPrompt, openAiAudioSpeechOptions));
		
		byte[] audioBytes = textToSpeechResponse.getResult().getOutput();
		
		if (textToSpeechResponse == null || textToSpeechResponse.getResult() == null) {
		    throw new RuntimeException("No response from TTS model");
		}

		try(FileOutputStream fileOutputStream = new FileOutputStream("AudioOutput.MP3")){
			fileOutputStream.write(audioBytes);
		} catch (FileNotFoundException e) {
			System.err.println("File Error in Genrating Audio for prompt...."+e);
		} catch (IOException e) {
			System.err.println("Io Error in Genrating Audio for prompt...."+e);
		}
		
		return "Audio File generate from AI, check output file in project root folder";
	} 
}
