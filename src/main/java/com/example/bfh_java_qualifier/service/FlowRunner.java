package com.example.bfh_java_qualifier.service;

import com.example.bfh_java_qualifier.model.GenerateWebhookRequest;
import com.example.bfh_java_qualifier.model.GenerateWebhookResponse;
import com.example.bfh_java_qualifier.model.SolutionRecord;
import com.example.bfh_java_qualifier.repo.SolutionRecordRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class FlowRunner {
    private static final Logger log = LoggerFactory.getLogger(FlowRunner.class);

    private final HiringApiClient api;
    private final SqlSolverService solver;
    private final SolutionRecordRepository repo;

    @Value("${bfh.name}") private String name;
    @Value("${bfh.regNo}") private String regNo;
    @Value("${bfh.email}") private String email;

    @EventListener(ApplicationReadyEvent.class)
    public void runFlow() {
        GenerateWebhookRequest req = new GenerateWebhookRequest(name, regNo, email);

        api.generateWebhook(req)
                .flatMap(this::handleAndSubmit)
                .doOnError(e -> log.error("Flow failed", e))
                .block();
    }

    private reactor.core.publisher.Mono<String> handleAndSubmit(GenerateWebhookResponse resp) {
        String questionType = solver.questionType();
        String finalQuery = solver.finalQuery();

        SolutionRecord record = SolutionRecord.builder()
                .regNo(regNo)
                .questionType(questionType)
                .finalQuery(finalQuery)
                .createdAt(Instant.now())
                .build();

        repo.save(record);
        log.info("Stored solution in DB: {}", finalQuery);

        return api.submitSolution(resp.getWebhookUrl(), resp.getAccessToken(), finalQuery)
                .doOnSuccess(r -> log.info("Successfully submitted query ✅"))
                .doOnError(e -> log.error("Submission failed ❌", e));
    }

}
