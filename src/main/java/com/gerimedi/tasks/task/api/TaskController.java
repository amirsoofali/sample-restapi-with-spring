package com.gerimedi.tasks.task.api;

import com.gerimedi.tasks.TasksApplication;
import com.gerimedi.tasks.exceptions.NotFoundException;
import com.gerimedi.tasks.task.TaskService;
import com.gerimedi.tasks.task.domain.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(TasksApplication.API_VERSION)
public class TaskController implements TaskEndpoint {

    public static final String CSV_TYPE = "text/csv";
    private final TaskService service;

    public TaskController(final TaskService service) {
        this.service = service;
    }

    @Operation(summary = "Handles file upload requests.")
    @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSV_TYPE.equals(file.getContentType())) {
            try {
                service.saveAllByFile(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
            } catch (
                    Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" " + message + " \"");
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" " + message + " \"");
    }

    @Override
    public Collection<TaskDTO> getAllTasks() {
        final Collection<Task> tasks = this.service.getTasks();
        return tasks.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO getTask(@PathVariable("code") final String code) throws NotFoundException {
        final Task task = this.service.getTask(code);
        return this.toDTO(task);
    }

    @Override
    public void deleteAll() {
        this.service.deleteAll();
    }


    private TaskDTO toDTO(final Task task) {
        return TaskDTO.builder().code(task.getCode()).source(task.getSource()).codeListCode(task.getCodeListCode())
                .longDescription(task.getLongDescription()).displayValue(task.getDisplayValue()).fromDate(task.getFromDate())
                .toDate(task.getToDate()).sortingPriority(task.getSortingPriority()).build();
    }


}
