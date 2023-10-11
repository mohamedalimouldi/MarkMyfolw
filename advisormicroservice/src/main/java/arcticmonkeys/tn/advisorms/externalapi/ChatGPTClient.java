package arcticmonkeys.tn.advisorms.externalapi;

import arcticmonkeys.tn.advisorms.configs.FeignClientConfiguration;
import arcticmonkeys.tn.advisorms.dto.ChatGptRequestDto;
import arcticmonkeys.tn.advisorms.dto.ChatGptResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "chatgpt", url = "https://api.openai.com/v1/", configuration = FeignClientConfiguration.class)
public interface ChatGPTClient {

    @PostMapping("/engines/davinci/completions")
    ChatGptResponseDto askQuestion(ChatGptRequestDto requestDto);

}