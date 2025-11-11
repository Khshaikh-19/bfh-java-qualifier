package com.example.bfh_java_qualifier.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolutionRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String regNo;
    private String questionType;

    @Column(length = 4000)
    private String finalQuery;

    private Instant createdAt;
}
