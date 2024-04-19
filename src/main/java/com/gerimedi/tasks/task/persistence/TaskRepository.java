package com.gerimedi.tasks.task.persistence;

import org.springframework.data.repository.CrudRepository;


public interface TaskRepository extends CrudRepository<TaskEntity, String> {

}
