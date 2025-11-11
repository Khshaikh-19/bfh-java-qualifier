package com.example.bfh_java_qualifier.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SqlSolverService {
    @Value("${bfh.regNo}")
    private String regNo;

    @Value("${bfh.finalQueryOdd}")
    private String finalQueryOdd;

    @Value("${bfh.finalQueryEven}")
    private String finalQueryEven;

    public String questionType() {
        String digits = regNo.replaceAll("\\D", "");
        if (digits.isEmpty()) return "UNKNOWN";
        int lastTwo = Integer.parseInt(digits.substring(Math.max(0, digits.length() - 2)));
        return (lastTwo % 2 == 0) ? "EVEN" : "ODD";
    }

    public String finalQuery() {
        return "ODD".equals(questionType()) ? finalQueryOdd : finalQueryEven;
    }
}
