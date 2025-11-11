package com.example.bfh_java_qualifier.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateWebhookRequest {
    private String name;
    private String regNo;
    private String email;

}
