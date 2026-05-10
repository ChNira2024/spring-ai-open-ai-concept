package com.niranjana.springai.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

import com.niranjana.springai.exception.AiServiceException;

@Service
public class ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageService.class);
    private final OpenAiImageModel openAiImageModel;

    public ImageService(OpenAiImageModel openAiImageModel) {
        this.openAiImageModel = openAiImageModel;
    }

    public ImageResponse generateImage(String prompt, String quality, int n, int width, int height){

        log.info("Generating image for prompt: {}", prompt);

        try {
            return openAiImageModel.call(
                    new ImagePrompt(prompt,
                            OpenAiImageOptions.builder()
                                    .model("dall-e-2")
                                    .quality(quality)
                                    .N(n)
                                    .height(height)
                                    .width(width)
                                    .build())
            );
        } catch (Exception e) {
            log.error("Error while generating image", e);
            throw new AiServiceException("Image generation failed");
        }
    }
}