package com.gerimedi.tasks.task.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
@Entity
public class TaskEntity {

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "CODE_LIST_CODE")
    private String codeListCode;

    @Column(name = "DISPLAY_VALUE")
    private String displayValue;

    @Column(name = "LONG_DESCRIPTION")
    private String longDescription;

    @Column(name = "FROM_DATE")
    private String fromDate;

    @Column(name = "TO_DATE")
    private String toDate;

    @Column(name = "SORTING_PRIORITY")
    private Integer sortingPriority;


}
