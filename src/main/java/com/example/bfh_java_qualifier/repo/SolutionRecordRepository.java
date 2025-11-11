package com.example.bfh_java_qualifier.repo;
import com.example.bfh_java_qualifier.model.SolutionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SolutionRecordRepository extends JpaRepository<SolutionRecord, Long> {
}
