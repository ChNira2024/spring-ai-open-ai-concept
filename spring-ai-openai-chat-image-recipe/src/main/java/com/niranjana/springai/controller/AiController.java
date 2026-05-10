package com.niranjana.springai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niranjana.springai.service.ChatService;
import com.niranjana.springai.service.ImageService;
import com.niranjana.springai.service.RecipeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/ai")
@Tag(name = "AI Controller", description = "APIs for Chat, Image and Recipe Generation")
public class AiController {

    private final ChatService chatService;
    private final ImageService imageService;
    private final RecipeService recipeService;

    public AiController(ChatService chatService,ImageService imageService,RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @Operation(
            summary = "Generate Chat Response",
            description = "Takes a user prompt and returns AI-generated response"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated response"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/chat")
    public String chat(
            @Parameter(description = "User input prompt", example = "Explain Java in simple terms")
            @RequestParam String prompt) {

        return chatService.getResponse(prompt);
    }

    @Operation(
            summary = "Generate Recipe",
            description = "Creates a recipe based on ingredients, cuisine, and dietary restrictions"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe generated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/recipe")
    public String recipe(
            @Parameter(description = "Ingredients list", example = "chicken, rice, spices")
            @RequestParam String ingredients,

            @Parameter(description = "Cuisine type", example = "Indian")
            @RequestParam String cuisine,

            @Parameter(description = "Dietary restrictions", example = "low fat")
            @RequestParam String diet) {

        return recipeService.createRecipe(ingredients, cuisine, diet);
    }

    @Operation(
            summary = "Generate Image",
            description = "Generates AI image based on prompt and configuration"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Image generated successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/image")
    public Object generateImage(

            @Parameter(description = "Image prompt", example = "A futuristic city at sunset")
            @RequestParam String prompt,

            @Parameter(description = "Image quality", example = "hd")
            @RequestParam(defaultValue = "standard") String quality,

            @Parameter(description = "Number of images", example = "1")
            @RequestParam(defaultValue = "1") int n,

            @Parameter(description = "Image width", example = "1024")
            @RequestParam(defaultValue = "1024") int width,

            @Parameter(description = "Image height", example = "1024")
            @RequestParam(defaultValue = "1024") int height
    ) {
        return imageService.generateImage(prompt, quality, n, width, height);
    }
}
