package com.gerimedi.tasks.task.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TaskDTO {

    private String code;
    private String source;
    private String codeListCode;
    private String displayValue;
    private String longDescription;
    private String fromDate;
    private String toDate;
    private Integer sortingPriority;

}
