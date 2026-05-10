package com.niranjana.springai.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import com.niranjana.springai.exception.AiServiceException;

import java.util.Map;

@Service
public class RecipeService {
		private static final Logger log = LoggerFactory.getLogger(RecipeService.class);

		private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions) {

        log.info("Creating recipe with ingredients: {}", ingredients);

        try {
            var template = """
                    I want to create a recipe using the following ingredients: {ingredients}.
                    The cuisine type I prefer is {cuisine}.
                    Please consider the following dietary restrictions: {dietaryRestrictions}.
                    Provide title, ingredients list, and instructions.
                    """;

            PromptTemplate promptTemplate = new PromptTemplate(template);

            Prompt prompt = promptTemplate.create(Map.of(
                    "ingredients", ingredients,
                    "cuisine", cuisine,
                    "dietaryRestrictions", dietaryRestrictions
            ));

            return chatModel.call(prompt).getResult().getOutput().getText();

        } catch (Exception e) {
            log.error("Error while generating recipe", e);
            throw new AiServiceException("Recipe generation failed");
        }
    }
}