package com.gerimedi.tasks.task.api;

import com.gerimedi.tasks.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Tag(name = "Tasks")
public interface TaskEndpoint {

    String TASKS_URL = "/tasks";

    @Operation(summary = "Get all tasks", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class))),
    })
    @GetMapping(TASKS_URL)
    Collection<TaskDTO> getAllTasks();

    @Operation(summary = "Get a task via his code", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskDTO.class))),
    })
    @GetMapping(TASKS_URL + "/{code}")
    TaskDTO getTask(@PathVariable("code") String code) throws NotFoundException;


    @Operation(description = "Delete all tasks.", summary = "Delete all tasks.")
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    @DeleteMapping(TASKS_URL)
    void deleteAll();
}
