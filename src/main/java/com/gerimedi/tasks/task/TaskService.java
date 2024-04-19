package com.gerimedi.tasks.task;

import com.gerimedi.tasks.exceptions.NotFoundException;
import com.gerimedi.tasks.task.domain.Task;
import com.gerimedi.tasks.task.persistence.TaskEntity;
import com.gerimedi.tasks.task.persistence.TaskRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(final TaskRepository repository) {
        this.repository = repository;
    }

    private static List<TaskEntity> csvToEntity(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).setIgnoreHeaderCase(true).setTrim(true).build())) {

            return getTaskEntities(csvParser);
        } catch (
                IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    private static List<TaskEntity> getTaskEntities(CSVParser csvParser) {
        List<TaskEntity> tasks = new ArrayList<>();

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
            TaskEntity task = new TaskEntity(
                    csvRecord.get(2),
                    csvRecord.get(0),
                    csvRecord.get(1),
                    csvRecord.get(3),
                    csvRecord.get(4),
                    csvRecord.get(5),
                    csvRecord.get(6),
                    !csvRecord.get(7).isEmpty() ? Integer.parseInt(csvRecord.get(7)) : null);
            tasks.add(task);
        }
        return tasks;
    }

    public Collection<Task> getTasks() {
        return Streamable.of(this.repository.findAll()).map(this::toDomain).toList();
    }

    public Task getTask(final String code) throws NotFoundException {
        final Optional<TaskEntity> opt = this.repository.findById(code);
        return opt.map(this::toDomain).orElseThrow(() -> new NotFoundException("Not found archived task with code : " + code));
    }


    public void saveAllByFile(MultipartFile file) {
        try {
            List<TaskEntity> entities = csvToEntity(file.getInputStream());
            repository.saveAll(entities);
        } catch (
                IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    private Task toDomain(final TaskEntity entity) {
        return Task.builder().code(entity.getCode()).source(entity.getSource()).codeListCode(entity.getCodeListCode())
                .longDescription(entity.getLongDescription()).displayValue(entity.getDisplayValue()).fromDate(entity.getFromDate() != null ? entity.getFromDate().replace("-", "/") : null)
                .toDate(entity.getToDate()).sortingPriority(entity.getSortingPriority()).build();
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
