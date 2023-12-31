package com.wanted.workwave.workflow.controller;

import com.wanted.workwave.common.response.ApiResponse;
import com.wanted.workwave.workflow.dto.request.WorkMoveRequest;
import com.wanted.workwave.workflow.dto.request.WorkRequest;
import com.wanted.workwave.workflow.dto.response.WorkResponse;
import com.wanted.workwave.workflow.service.WorkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "작업(티켓)")
@RestController
@RequestMapping("/workflows/{workflowId}/works")
@RequiredArgsConstructor
public class WorkController {

    private final WorkService workService;

    @PostMapping
    public ApiResponse<WorkResponse> createWork(
            @RequestAttribute Long userId,
            @PathVariable Long workflowId,
            @Valid @RequestBody WorkRequest request) {
        return ApiResponse.created(workService.createWork(userId, workflowId, request));
    }

    @PutMapping("/{workId}")
    public ApiResponse<WorkResponse> updateWork(
            @RequestAttribute Long userId,
            @PathVariable Long workflowId,
            @PathVariable Long workId,
            @Valid @RequestBody WorkRequest request) {
        return ApiResponse.ok(workService.updateWork(userId, workflowId, workId, request));
    }

    @PatchMapping("/{workId}/move")
    public ApiResponse<WorkResponse> moveWork(
            @RequestAttribute Long userId,
            @PathVariable Long workflowId,
            @PathVariable Long workId,
            @Valid @RequestBody WorkMoveRequest request) {
        return ApiResponse.ok(workService.moveWork(userId, workflowId, workId, request));
    }

    @DeleteMapping("/{workId}")
    public ApiResponse<Void> deleteWork(
            @RequestAttribute Long userId,
            @PathVariable Long workflowId,
            @PathVariable Long workId) {
        workService.deleteWork(userId, workflowId, workId);
        return ApiResponse.noContent();
    }
}
