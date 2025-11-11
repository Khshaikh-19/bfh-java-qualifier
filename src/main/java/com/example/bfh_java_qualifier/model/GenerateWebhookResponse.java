package com.example.bfh_java_qualifier.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class GenerateWebhookResponse {
    @JsonProperty("webhook")
    private String webhookUrl;

    @JsonProperty("accessToken")
    private String accessToken;
}
