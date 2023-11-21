package com.wanted.workwave.workflow.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long workflowId;
    private Long assigneeId;
    private int position;
    private String title;
    private Tag tag;
    private double workload;
    private LocalDateTime deadline;

    @Builder
    public Work(Long workflowId, Long assigneeId, String title, Tag tag, double workload, LocalDateTime deadline) {
        this.workflowId = workflowId;
        this.assigneeId = assigneeId;
        this.title = title;
        this.tag = tag;
        this.workload = workload;
        this.deadline = deadline;
    }
}
