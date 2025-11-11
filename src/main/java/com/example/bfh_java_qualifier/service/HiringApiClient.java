package com.example.bfh_java_qualifier.service;
import com.example.bfh_java_qualifier.model.GenerateWebhookRequest;
import com.example.bfh_java_qualifier.model.GenerateWebhookResponse;
import com.example.bfh_java_qualifier.model.SubmissionPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class HiringApiClient {
    private final WebClient webClient;

    @Value("${bfh.generateWebhookPath}")
    private String generateWebhookPath;

    public Mono<GenerateWebhookResponse> generateWebhook(GenerateWebhookRequest body) {
        return webClient.post()
                .uri(generateWebhookPath)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(GenerateWebhookResponse.class);
    }

    public Mono<String> submitSolution(String webhookUrl, String jwtAccessToken, String finalQuery) {
        return WebClient.create()
                .post()
                .uri(webhookUrl)
                .header(HttpHeaders.AUTHORIZATION, jwtAccessToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new SubmissionPayload(finalQuery))
                .retrieve()
                .bodyToMono(String.class);
    }

}
